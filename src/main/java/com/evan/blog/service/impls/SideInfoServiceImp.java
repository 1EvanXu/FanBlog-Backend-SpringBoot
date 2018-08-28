package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.model.Category;
import com.evan.blog.pojo.SideInfoItem;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.service.ArticleService;
import com.evan.blog.service.SideInfoService;
import com.evan.blog.util.RedisOperator;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service(value = "sideInfoService")
public class SideInfoServiceImp implements SideInfoService {

    @Autowired
    RedisOperator redisOperator;

    @Autowired
    CategoryDao categoryDao;

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<SideInfoItem> getLatestPublishedArticle() {
        List<SideInfoItem> sideInfoItems = new ArrayList<>();
        List<Article> latestArticles = articleDao.selectLatestArticles(8);
        latestArticles.forEach((a) -> {
            sideInfoItems.add(new SideInfoItem(a.getPubId(), a.getDraft().getTitle(), null));
        });

        return sideInfoItems;
    }

    @Override
    public List<SideInfoItem> getPopularPublishedArticle() {
        final String key = "pub_articles_rank:";
        List<SideInfoItem> sideInfoItems = new ArrayList<>();

        Set<ZSetOperations.TypedTuple<String>> tuples = redisOperator.zrevrange(key, 0, 9);

        tuples.forEach(stringTypedTuple -> {
            if (stringTypedTuple.getScore() > 0) {
                // get the id of article, the query article title from DB.
                // then create a SideinfoItem from these info.
                Long id = Long.parseLong(stringTypedTuple.getValue());
                String name = articleDao.selectArticleTitleByPubId(id);
                Double score = stringTypedTuple.getScore();
                sideInfoItems.add(new SideInfoItem(id, name, score));

            }
        });
        return sideInfoItems;
    }

    /**
     * @Description Get Category from DB by the order of NumberOfIncluded Articles
     *              and package the to SideInfoItem Object
     * @return List<SideInfoItem>
     */
    @Override
    public List<SideInfoItem> getCategoriesInfo() {
        List<SideInfoItem> sideInfoItems = new ArrayList<>();
        PageHelper.startPage(1, 8);
        List<Category> categories = categoryDao.selectCategoriesOrderByIncludedArticles();
        categories.forEach((category) -> {
                if (category.getNumberOfIncludedPubArticles() > 0) {
                    sideInfoItems.add(
                            new SideInfoItem(
                                    category.getId(),
                                    category.getName(),
                                    (double) category.getNumberOfIncludedPubArticles()
                            ));
                }
            }
        );
        return sideInfoItems;
    }

}
