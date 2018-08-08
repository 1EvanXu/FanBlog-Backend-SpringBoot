package com.evan.blog.service;

import com.evan.blog.model.Article;

public interface EditorService {
    long saveMdContentInCache();
    long saveDraft(Article article);
}
