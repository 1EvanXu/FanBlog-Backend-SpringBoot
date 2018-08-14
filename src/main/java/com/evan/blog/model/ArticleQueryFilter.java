package com.evan.blog.model;

import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.model.enums.Order;

public class ArticleQueryFilter extends QueryFilter{

    private ArticleStatus articleStatus;

    public ArticleQueryFilter() {}

    public ArticleQueryFilter(String orderField, Order order, ArticleStatus articleStatus) {
        super(orderField, order);
        this.articleStatus = articleStatus;
    }

    public Integer getArticleStatus() {
        return articleStatus.getStatusCode();
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }
}


