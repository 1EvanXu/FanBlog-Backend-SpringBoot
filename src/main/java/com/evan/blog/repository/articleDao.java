package com.evan.blog.repository;

import com.evan.blog.model.Article;
import com.evan.blog.model.TempArticle;
import com.evan.blog.model.QueryFilter;

import java.util.List;

public interface articleDao {
    List<Article> selectArticles(QueryFilter filter);
    List<Article> selectArticlesByCategoryId(long categoryId);
    List<Article> selectLatestArticles(Integer limit);
    Article selectArticleByPubId(long pubId);
    String selectArticleTitleByPubId(long pubId);
    void insertArticle(Article article);
    void insertTempArticle(TempArticle tempArticle);
    void deleteArticle(long pubId);
    int selectCountOfArticlesByCategory(long categoryId);
}
