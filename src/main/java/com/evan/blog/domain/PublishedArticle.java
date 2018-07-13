package com.evan.blog.domain;

import com.evan.blog.domain.states.PublishedArticleType;

import java.sql.Timestamp;

public class PublishedArticle {
    private Integer id;
    private Integer pubId;
    private Timestamp pubTime;
    private PublishedArticleType type;
    private Article article;
    private Category category;

    public PublishedArticle() {}

    public PublishedArticle(Integer pubId, PublishedArticleType type, Article article, Category category) {
        this.pubId = pubId;
        this.type = type;
        this.article = article;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public PublishedArticleType getType() {
        return type;
    }

    public void setType(PublishedArticleType type) {
        this.type = type;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "PublishedArticle{" +
                "id=" + id +
                ", pubId=" + pubId +
                ", pubTime=" + pubTime +
                ", type=" + type +
                ", article=" + article +
                ", category=" + category +
                '}';
    }
}
