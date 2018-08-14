package com.evan.blog.pojo.management;

import com.evan.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"status", "htmlContent", "markdownContent"})
public class DraftsManagementListItem extends Article {
    public DraftsManagementListItem() { }
}
