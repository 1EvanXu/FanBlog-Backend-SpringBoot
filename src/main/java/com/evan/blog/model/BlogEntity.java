package com.evan.blog.model;

import java.io.Serializable;

public abstract class BlogEntity implements Serializable {
    protected Integer id;

    BlogEntity() {
    }

    protected BlogEntity(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
