package com.evan.blog.pojo.management;

import com.evan.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"article", "category", "commentariesCount"})
public class ArticlesManagementListItem extends Article {

    private String title;

    public ArticlesManagementListItem() {
        super();
    }

    public ArticlesManagementListItem(Article article) {
        this.setId(article.getPubId());
        this.setPubId(article.getPubId());
        this.setTitle(article.getDraft().getTitle());
        this.setType(article.getType());
        this.setPubTime(article.getPubTime());
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
