package com.evan.blog.service;

import com.evan.blog.model.Article;
import com.evan.blog.model.Category;
import com.evan.blog.pojo.Draft;

import java.util.List;

public interface EditorService {
    long generateTempArticleId();

    long saveDraftInCache(Draft draft) throws IllegalAccessException;

    Draft getArticleContent(Integer articleId);

    Integer saveArticle(Article article) throws IllegalAccessException;

    List<Category> searchCategoryByName(String keyword);
}
