package com.evan.blog.service.impls;

import com.evan.blog.model.Category;
import com.evan.blog.pojo.SideInfoItem;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.service.SideInfoService;
import com.evan.blog.util.JsonUtil;
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

    @Override
    public List<SideInfoItem> getLatestPublishedArticle() {
        final String key = "latest_pub_articles:";
        List<SideInfoItem> sideInfoItems = new ArrayList<>();
        List<String> latestPubArticles = redisOperator.lrange(key, 0, -1);
        latestPubArticles.forEach((s) -> {
            String[] strings = s.split(":");
            sideInfoItems.add(new SideInfoItem(Integer.parseInt(strings[0]), strings[1], null));
        });
        return sideInfoItems;
    }

    @Override
    public List<SideInfoItem> getPopularPublishedArticle() {
        final String key = "pub_articles_rank:";
        List<SideInfoItem> sideInfoItems = new ArrayList<>();

        Set<ZSetOperations.TypedTuple<String>> tuples = redisOperator.zrevrange(key, 0, 8);

        tuples.forEach(stringTypedTuple -> {
            if (stringTypedTuple.getScore() > 0) {
                String info[] = stringTypedTuple.getValue().split(":");
                Integer id = Integer.parseInt(info[0]);
                String name = info[1];
                Integer score = stringTypedTuple.getScore().intValue();
                sideInfoItems.add(new SideInfoItem(id, name, score));

            }
        });
        return sideInfoItems;
    }

    /**
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
                                    category.getNumberOfIncludedPubArticles()
                            ));
                }
            }
        );
        return sideInfoItems;
    }

}
