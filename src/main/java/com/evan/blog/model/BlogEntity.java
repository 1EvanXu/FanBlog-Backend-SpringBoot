package com.evan.blog.model;

public abstract class BlogEntity {
    protected Integer id;

    protected BlogEntity() {
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
