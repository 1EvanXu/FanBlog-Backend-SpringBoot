package com.evan.blog.pojo.management;

import com.evan.blog.model.Draft;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"createdTime", "status", "htmlContent", "markdownContent"})
public class DeletedArticlesManagementListItem extends Draft {
    public DeletedArticlesManagementListItem() {
    }

    public DeletedArticlesManagementListItem(Draft draft) {
        this.setId(draft.getId());
        this.setTitle(draft.getTitle());
        this.setLatestEditedTime(draft.getLatestEditedTime());
    }
}
