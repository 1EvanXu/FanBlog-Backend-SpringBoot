package com.evan.blog.model;

import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.model.enums.Order;

public class QueryFilter {
    private String orderField;
    private Order order = Order.Desc;
    private ArticleStatus articleStatus;

    public QueryFilter() {}

    public QueryFilter(String orderField, Order order, ArticleStatus articleStatus) {
        this.orderField = orderField;
        this.order = order;
        this.articleStatus = articleStatus;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getArticleStatus() {
        return articleStatus.getStatusCode();
    }

    public void setArticleStatus(ArticleStatus articleStatus) {
        this.articleStatus = articleStatus;
    }
}


