package com.evan.blog.service.interfaces;

import com.evan.blog.model.PublishedArticle;
import com.github.pagehelper.PageInfo;

public interface PublishedArticleService {
    PageInfo<PublishedArticle> getAllPublishedArticles(int pageIndex);
    PageInfo<PublishedArticle> getPublishedArticlesByCategoryId(Integer categoryId, Integer pageIndex);
}
