package com.evan.blog.service.impls;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.repository.PublishedArticleDao;
import com.evan.blog.service.interfaces.PublishedArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "publishedArticleService")
public class PublishedArticleServiceImp implements PublishedArticleService {

    private int pageSize = 2;

    @Autowired
    PublishedArticleDao publishedArticleDao;

    @Override
    public PageInfo<PublishedArticle> getAllPublishedArticles(int pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticles();

        return new PageInfo<>(publishedArticles);
    }

    @Override
    public PageInfo<PublishedArticle> getPublishedArticlesByCategoryId(Integer categoryId, Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticlesByCategoryId(categoryId);
        return new PageInfo<>(publishedArticles);
    }
}
