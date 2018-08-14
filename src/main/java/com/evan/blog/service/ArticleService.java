package com.evan.blog.service;

import com.evan.blog.model.Article;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.ArticleStatus;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface ArticleService {
    PageInfo<Article> getArticles(Integer pageIndex, QueryFilter filter);
    Article queryArticleById(int id);
    void addArticle(Article article);
    void updateArticle(Article article);
    void updateArticleStatus(ArticleStatus status, List<Integer> ids);
    void removeArticles(List<Integer> articleIds);
}
