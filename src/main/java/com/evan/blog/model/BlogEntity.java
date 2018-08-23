package com.evan.blog.model;

import java.io.Serializable;

public abstract class BlogEntity implements Serializable {
    protected Long id;

    BlogEntity() {
    }

    protected BlogEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
