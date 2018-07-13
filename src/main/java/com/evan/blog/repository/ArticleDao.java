package com.evan.blog.repository;

import com.evan.blog.domain.Article;
import com.evan.blog.domain.states.ArticleStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    List<Article> getAllArticlesByCreatedTime(@Param("order") boolean oderByDesc);

    Article selectArticleById(int id);
    void insertArticle(Article article);
    void updateArticleStatus(@Param("statusCode") ArticleStatus status, @Param("id") int id);
    void updateArticle(Article article);
    void deleteArticle(int id);
    int selectArticlesCount();
}

