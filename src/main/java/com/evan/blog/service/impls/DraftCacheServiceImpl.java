package com.evan.blog.service.impls;

import com.evan.blog.model.Draft;
import com.evan.blog.model.Category;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.pojo.TempDraft;
import com.evan.blog.service.DraftService;
import com.evan.blog.service.CategoryService;
import com.evan.blog.service.DraftCacheService;
import com.evan.blog.util.JsonUtil;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

@Service(value = "draftCacheService")
public class DraftCacheServiceImpl implements DraftCacheService {

    private static final long DURATION = 7200L;  //7200 seconds, 2 hours.

    private final String keyPrefix = "md_content:";

    private static final Semaphore semaphore = new Semaphore(1);

    @Autowired
    RedisOperator redisOperator;

    @Autowired
    DraftService draftService;

    @Autowired
    CategoryService categoryService;

    @Override
    public long generateTempArticleId() {
        String key = "temp_article_id:";
        return redisOperator.incr(key, 1);
    }

    @Override
    public long saveDraftInCache(TempDraft tempDraft) throws IllegalAccessException {
        Long tempArticleId = tempDraft.getTempDraftId();
        Long articleId = tempDraft.getId();

        if (tempArticleId == null && articleId == null)
            throw new IllegalAccessException("the property tempArticleId and id can't be null same time");

        String key = null;
        String tempKey = tempArticleId != null ? keyPrefix + "d:" + tempArticleId : null;

        if (tempKey == null) {
            key =  keyPrefix + "a:" + articleId;
        } else {
            if (articleId == null) {
                key = tempKey;
            } else {
                key =  keyPrefix + "a:" + articleId;
                redisOperator.del(tempKey);
            }
        }

        String content = JsonUtil.objectToJson(tempDraft);

        redisOperator.set(key, content, randomExpireTime());

        return System.currentTimeMillis();
    }

    @Override
    public TempDraft getDraftContent(Long draftId) {
        String key = keyPrefix + "a:"+ draftId;
        String value = redisOperator.get(key);

        TempDraft tempDraft = null;

        if (value != null && !value.equals("")) {
            tempDraft = JsonUtil.jsonToPojo(value, TempDraft.class);
        } else {
            // 加锁防止缓存穿透
            try {
                semaphore.acquire();
                Draft draft = draftService.queryDraftById(draftId);
                if (draft.getStatus() != DraftStatus.Editing) {
                    throw new IllegalAccessException("Can edit only when the status of draft is Editing");
                }
                tempDraft = new TempDraft(draft);
            } catch (InterruptedException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            } finally {
                semaphore.release();
            }
        }

        return tempDraft;

    }

    @Override
    public Long saveDraft(Draft draft) throws IllegalAccessException {
        TempDraft tempDraft = new TempDraft(draft);
        saveDraftInCache(tempDraft);
        if (draft.getId() == null) {
            draftService.addDraft(draft);
            return draft.getId();
        }

        draftService.updateDraft(draft);
        return draft.getId();
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
