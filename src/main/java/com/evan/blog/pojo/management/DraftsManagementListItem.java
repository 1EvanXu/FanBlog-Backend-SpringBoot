package com.evan.blog.pojo.management;

import com.evan.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"status", "htmlContent", "markdownContent"})
public class DraftsManagementListItem extends Article {
    public DraftsManagementListItem() { }

    public DraftsManagementListItem(Article article) {
        this.setId(article.getId());
        this.setTitle(article.getTitle());
        this.setCreatedTime(article.getCreatedTime());
        this.setLatestEditedTime(article.getLatestEditedTime());
    }
}
