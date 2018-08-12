package com.evan.blog.repository;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.model.PublishingArticle;

import java.util.List;

public interface PublishedArticleDao {
    List<PublishedArticle> selectPublishedArticles();
    List<PublishedArticle> selectPublishedArticlesByCategoryId(Integer categoryId);
    PublishedArticle selectPublishedArticleByPubId(int pubId);
    String selectPublishedArticleTitleByPubId(int pubId);
    void insertPublishedArticle(PublishedArticle publishedArticle);
    void insertPublishingArticle(PublishingArticle publishingArticle);
    void deletePublishedArticle(int pubId);
    Integer selectCountOfPubArticlesByCategory(int categoryId);
}
