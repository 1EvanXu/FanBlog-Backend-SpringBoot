package com.evan.blog.pojo.management;

import com.evan.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"createdTime", "latestEditedTime", "status", "htmlContent", "markdownContent"})
public class DeletedArticlesManagementListItem extends Article {
}
