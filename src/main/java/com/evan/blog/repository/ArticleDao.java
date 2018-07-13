package com.evan.blog.respository;

import com.evan.blog.domain.Article;
import com.evan.blog.domain.states.ArticleStatus;
import org.apache.ibatis.annotations.Param;

public interface ArticleDao {
    Article getArticleById(int id);
    void addArticle(Article article);
    void updateArticleStatus(@Param("statusCode") ArticleStatus status, @Param("id") int id);
    void updateArticle(Article article);
    void deleteArticlePermanently(int id);
}

