package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.model.Category;
import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.pojo.Draft;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.service.ArticleService;
import com.evan.blog.service.CategoryService;
import com.evan.blog.service.EditorService;
import com.evan.blog.util.JsonUtil;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

@Service(value = "editorService")
public class EditorServiceImpl implements EditorService {

    private static final long DURATION = 7200L;  //7200 seconds, 2 hours.

    private final String keyPrefix = "md_content:";

    private static final Semaphore semaphore = new Semaphore(1);

    @Autowired
    RedisOperator redisOperator;

    @Autowired
    ArticleService articleService;

    @Autowired
    CategoryService categoryService;

    @Override
    public long generateTempArticleId() {
        String key = "temp_article_id:";
        return redisOperator.incr(key, 1);
    }

    @Override
    public long saveDraftInCache(Draft draft) throws IllegalAccessException {
        Long tempArticleId = draft.getTempArticleId();
        String key;
        String tempKey = null;
        if(tempArticleId != null) {
            tempKey = keyPrefix + "d:" + tempArticleId;
        }
        if (tempKey != null && draft.getId() != null) {
            key = keyPrefix + "a:" + draft.getId();
            redisOperator.del(tempKey);
        } else if (tempKey != null){
            key = tempKey;
        } else {
            throw new IllegalAccessException("the property tempArticleId and id can't be null same time");
        }

        String content = JsonUtil.objectToJson(draft);

        if (redisOperator.setIfAbsent(key, "")) {
            redisOperator.set(key, content, randomExpireTime());
        } else {
            redisOperator.set(key, content);
        }
        return System.currentTimeMillis();
    }

    @Override
    public Draft getArticleContent(Integer articleId) {
        String key = keyPrefix + "a:"+ articleId;
        String value = redisOperator.get(key);

        Draft draft = null;

        if (value != null && !value.equals("")) {
            draft = JsonUtil.jsonToPojo(value, Draft.class);
        } else {
            // 加锁防止缓存穿透
            try {
                semaphore.acquire();
                Article article = articleService.queryArticleById(articleId);
                if (article.getStatus() != ArticleStatus.Editing) {
                    throw new IllegalAccessException("Can edit only when the status of article is Editing");
                }
                draft = new Draft(article);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                semaphore.release();
            }
        }

        return draft;

    }

    @Override
    public Integer saveArticle(Article article) throws IllegalAccessException {
        Draft draft = new Draft(article);
        saveDraftInCache(draft);
        if (article.getId() == null) {
            articleService.addArticle(article);
            return article.getId();
        }

        articleService.updateArticle(article);
        return article.getId();
    }

    @Override
    public List<Category> searchCategoryByName(String keyword) {
        return categoryService.findCategoriesByName(keyword);
    }

    //防止缓存雪崩
    private long randomExpireTime() {
        Random random = new Random();
        long delta = (long) random.nextInt(120);
        return DURATION + delta;
    }
}
