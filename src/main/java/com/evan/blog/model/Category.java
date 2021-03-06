package com.evan.blog.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Category extends BlogEntity{

    private String name;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdTime;
    private Integer numberOfIncludedPubArticles;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getNumberOfIncludedPubArticles() {
        return numberOfIncludedPubArticles;
    }

    public void setNumberOfIncludedPubArticles(Integer numberOfIncludedPubArticles) {
        this.numberOfIncludedPubArticles = numberOfIncludedPubArticles;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdTime=" + createdTime +
                ", numberOfIncludedPubArticles=" + numberOfIncludedPubArticles +
                '}';
    }
}
