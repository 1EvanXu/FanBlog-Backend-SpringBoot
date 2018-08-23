package com.evan.blog.pojo.management;

import com.evan.blog.model.Draft;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"status", "htmlContent", "markdownContent"})
public class DraftsManagementListItem extends Draft {
    public DraftsManagementListItem() { }

    public DraftsManagementListItem(Draft draft) {
        this.setId(draft.getId());
        this.setTitle(draft.getTitle());
        this.setCreatedTime(draft.getCreatedTime());
        this.setLatestEditedTime(draft.getLatestEditedTime());
    }
}
