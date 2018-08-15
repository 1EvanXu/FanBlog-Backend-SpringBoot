package com.evan.blog.service;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.model.PublishingArticle;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.pojo.PublishedArticleDetails;
import com.evan.blog.pojo.PublishedArticleItem;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface PublishedArticleService {

    PageInfo<PublishedArticleItem> getAllPublishedArticleItems(Integer pageIndex);

    PageInfo<PublishedArticle> getPublishedArticlesByFilter(Integer pageIndex, QueryFilter filter);

    PageInfo<PublishedArticleItem> getPublishedArticleItemsByCategoryId(Integer categoryId, Integer pageIndex);

    PublishedArticleDetails getPublishedArticle(Integer pubId);

    List<PublishedArticle> getLatestPublishedArticles(Integer limit);

    String getTitleByPubId(Integer pubId);

    void addPublishedArticle(PublishingArticle publishingArticle);

    void deletePublishedArticles(List<Integer> pubIds) throws Exception;

}
