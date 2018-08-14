package com.evan.blog.model;

import com.evan.blog.model.enums.Order;

public class QueryFilter {
    private String orderField;
    private Order order = Order.Desc;

    public QueryFilter() {}

    public QueryFilter(String orderField, Order order) {
        this.orderField = orderField;
        this.order = order;
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

    @Override
    public String toString() {
        return "QueryFilter{" +
                "orderField='" + orderField + '\'' +
                ", order=" + order +
                '}';
    }
}


