package com.evan.blog.service;

import com.evan.blog.pojo.PublishedArticleDetails;
import com.evan.blog.pojo.PublishedArticleItem;
import com.github.pagehelper.PageInfo;

public interface PublishedArticleService {
    PageInfo<PublishedArticleItem> getAllPublishedArticleItems(Integer pageIndex);
    PageInfo<PublishedArticleItem> getPublishedArticleItemsByCategoryId(Integer categoryId, Integer pageIndex);
    PublishedArticleDetails getPublishedArticle(Integer pubId);
}
