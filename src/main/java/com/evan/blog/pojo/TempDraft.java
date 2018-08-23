package com.evan.blog.pojo;

import com.evan.blog.model.Draft;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"htmlContent", "createdTime", "latestEditedTime", "status"})
public class TempDraft extends Draft {
    private Long tempDraftId;

    public TempDraft() {
        super();
    }

    public Long getTempDraftId() {
        return tempDraftId;
    }

    public void setTempDraftId(Long tempDraftId) {
        this.tempDraftId = tempDraftId;
    }

    public TempDraft(Draft draft) {
        this.setId(draft.getId());
        this.setTitle(draft.getTitle());
        this.setMarkdownContent(draft.getMarkdownContent());
    }
}
