package com.evan.blog.pojo.management;

import com.evan.blog.model.PublishedArticle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"article", "category", "commentariesCount"})
public class PublishedArticlesManagementListItem extends PublishedArticle {

    private String title;

    public PublishedArticlesManagementListItem() {
        super();
    }

    public PublishedArticlesManagementListItem(PublishedArticle publishedArticle) {
        this.setPubId(publishedArticle.getPubId());
        this.setTitle(publishedArticle.getArticle().getTitle());
        this.setType(publishedArticle.getType());
        this.setPubTime(publishedArticle.getPubTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
