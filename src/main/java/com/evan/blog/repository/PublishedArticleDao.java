package com.evan.blog.repository;

import com.evan.blog.model.PublishedArticle;

import java.util.List;

public interface PublishedArticleDao {
    List<PublishedArticle> selectPublishedArticles();
    PublishedArticle selectPublishedArticleByPubId(int pubId);
    void insertPublishedArticle(PublishedArticle publishedArticle);
    void deletePublishedArticle(int pubId);
    Integer selectCountOfPubArticlesByCategory(int categoryId);
}
