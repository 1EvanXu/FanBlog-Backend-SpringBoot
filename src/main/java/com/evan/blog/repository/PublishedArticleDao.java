package com.evan.blog.repository;

import com.evan.blog.domain.PublishedArticle;

public interface PublishedArticleDao {
    PublishedArticle selectPublishedArticleByPubId(int pubId);
    int selectPublishedArticlesCount();
    void insertPublishedArticle(PublishedArticle publishedArticle);
    void deletePublishedArticle(int pubId);
}
