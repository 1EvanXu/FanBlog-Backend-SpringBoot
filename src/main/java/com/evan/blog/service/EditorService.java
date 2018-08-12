package com.evan.blog.service;

import com.evan.blog.model.Article;
import com.evan.blog.pojo.Draft;

public interface EditorService {
    long generateTempArticleId();

    long saveDraftInCache(Draft draft);

    Draft getArticleContent(Integer articleId);

    long saveArticle(Article article);
}
