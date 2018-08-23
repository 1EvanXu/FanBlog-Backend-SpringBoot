package com.evan.blog.model;

import com.evan.blog.model.enums.Order;
import com.evan.blog.model.enums.ArticleType;

public class ArticleQueryFilter extends QueryFilter {
    private ArticleType type;

    public ArticleQueryFilter() {
    }

    public ArticleQueryFilter(String orderField, Order order, ArticleType type) {
        super(orderField, order);
        this.type = type;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }
}
