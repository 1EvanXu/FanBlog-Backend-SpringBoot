package com.evan.blog.service;

import com.evan.blog.model.Article;
import com.evan.blog.model.TempArticle;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.pojo.ArticleItem;
import com.evan.blog.pojo.ArticleDetails;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ArticleService {

    PageInfo<ArticleItem> getAllArticleItems(Integer pageIndex);

    PageInfo<Article> getArticlesByFilter(Integer pageIndex, QueryFilter filter);

    PageInfo<ArticleItem> getArticleItemsByCategoryId(long categoryId, Integer pageIndex);

    ArticleDetails getArticle(long pubId);

    List<Article> getLatestArticles(Integer limit);

    String getTitleByPubId(long pubId);

    void addArticle(TempArticle tempArticle);

    void deleteArticles(List<Integer> pubIds) throws Exception;

}
