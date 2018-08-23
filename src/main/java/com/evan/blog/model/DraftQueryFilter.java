package com.evan.blog.model;

import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.model.enums.Order;

public class DraftQueryFilter extends QueryFilter{

    private DraftStatus status;

    public DraftQueryFilter() {}

    public DraftQueryFilter(String orderField, Order order, DraftStatus status) {
        super(orderField, order);
        this.status = status;
    }

    public Integer getStatus() {
        return status.getStatusCode();
    }

    public void setStatus(DraftStatus status) {
        this.status = status;
    }
}


