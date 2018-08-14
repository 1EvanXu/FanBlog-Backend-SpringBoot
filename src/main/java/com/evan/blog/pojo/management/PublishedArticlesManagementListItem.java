package com.evan.blog.pojo.management;

import com.evan.blog.model.PublishedArticle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"article", "category", "commentariesCount"})
public class PublishedArticlesManagementListItem extends PublishedArticle {
    public PublishedArticlesManagementListItem() {
        super();
    }
}
