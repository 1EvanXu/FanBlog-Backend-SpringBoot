package com.evan.blog.service.impls;

import com.evan.blog.pojo.SideInfoItem;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.service.SideInfoService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "SideInfoService")
public class SideInfoServiceImp implements SideInfoService {

    @Autowired
    RedisOperator redisOperator;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<SideInfoItem> getLatestPublishedArticle() {
        return null;
    }

    @Override
    public List<SideInfoItem> getCategoriesInfo() {
        return null;
    }

    @Override
    public List<SideInfoItem> getPopularPublishedArticle() {
        categoryDao.selectCategories();
        return null;
    }
}
