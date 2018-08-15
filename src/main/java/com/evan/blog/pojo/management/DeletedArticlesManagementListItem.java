package com.evan.blog.pojo.management;

import com.evan.blog.model.Article;
import com.evan.blog.model.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"createdTime", "status", "htmlContent", "markdownContent"})
public class DeletedArticlesManagementListItem extends Article {
    public DeletedArticlesManagementListItem() {
    }

    public DeletedArticlesManagementListItem(Article article) {
        this.setId(article.getId());
        this.setTitle(article.getTitle());
        this.setLatestEditedTime(article.getLatestEditedTime());
    }
}
