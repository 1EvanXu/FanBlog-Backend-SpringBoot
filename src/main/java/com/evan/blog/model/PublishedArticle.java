package com.evan.blog.model;

import com.evan.blog.model.enums.PublishedArticleType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class PublishedArticle extends BlogEntity {

    private Integer pubId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp pubTime;
    private PublishedArticleType type;
    private Article article;
    private Category category;
    private Integer commentariesCount = 0;

    public PublishedArticle() {}

    public PublishedArticle(Integer pubId, PublishedArticleType type, Article article, Category category) {
        this.pubId = pubId;
        this.type = type;
        this.article = article;
        this.category = category;
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

    public Integer getCommentariesCount() {
        return commentariesCount;
    }

    public void setCommentariesCount(Integer commentariesCount) {
        this.commentariesCount = commentariesCount;
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

    public PublishedArticleItem toInitialItem() {
        return new PublishedArticleItem(this);
    }
}
