package com.evan.blog.model;

import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.model.enums.Order;

public class ArticleQueryFilter extends QueryFilter{

    private ArticleStatus status;

    public ArticleQueryFilter() {}

    public ArticleQueryFilter(String orderField, Order order, ArticleStatus status) {
        super(orderField, order);
        this.status = status;
    }

    public Integer getStatus() {
        return status.getStatusCode();
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }
}


