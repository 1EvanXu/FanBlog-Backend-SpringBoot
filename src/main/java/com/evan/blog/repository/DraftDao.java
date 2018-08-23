package com.evan.blog.repository;

import com.evan.blog.model.Draft;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.DraftStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DraftDao {
    List<Draft> selectAllDrafts(QueryFilter queryFilter);

    Draft selectDraftById(long id);
    void insertDraft(Draft draft);
    void updateDraftStatus(@Param("statusCode") DraftStatus status, @Param("id") long id);
    void updateDraft(Draft draft);
    void deleteDraft(long id);
    int selectDraftsCount();
}

