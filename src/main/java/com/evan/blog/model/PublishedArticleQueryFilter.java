package com.evan.blog.model;

import com.evan.blog.model.enums.Order;
import com.evan.blog.model.enums.PublishedArticleType;

public class PublishedArticleQueryFilter extends QueryFilter {
    private PublishedArticleType type;

    public PublishedArticleQueryFilter() {
    }

    public PublishedArticleQueryFilter(String orderField, Order order, PublishedArticleType type) {
        super(orderField, order);
        this.type = type;
    }

    public PublishedArticleType getType() {
        return type;
    }

    public void setType(PublishedArticleType type) {
        this.type = type;
    }
}
