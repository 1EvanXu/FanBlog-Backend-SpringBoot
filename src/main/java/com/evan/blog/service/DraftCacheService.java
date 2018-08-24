package com.evan.blog.service;

import com.evan.blog.model.Draft;
import com.evan.blog.model.Category;
import com.evan.blog.pojo.TempDraft;

import java.util.List;

public interface DraftCacheService {
    long generateTempArticleId();

    long saveDraftInCache(TempDraft tempDraft) throws IllegalAccessException;

    TempDraft getDraftContent(Long draftId);

    Long saveDraft(TempDraft tempDraft);

    List<Category> searchCategoryByName(String keyword);
}
