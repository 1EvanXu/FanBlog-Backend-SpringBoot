package com.evan.blog.service.interfaces;

import com.evan.blog.model.PublishedArticle;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PublishedArticleService {
    PageInfo<PublishedArticle> getAllPublishedArticles(int pageIndex);
}
