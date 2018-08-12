package com.evan.blog.service;

import com.evan.blog.model.Article;
import com.evan.blog.model.enums.ArticleStatus;

public interface ArticleService {
    Article queryArticleById(int id);
    void addArticle(Article article);
    void updateArticle(Article article);
    void updateArticleStatus(ArticleStatus status, int id);
    void removeArticle(int id);
}
