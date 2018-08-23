package com.evan.blog.model;

import com.evan.blog.model.enums.ArticleType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Article extends BlogEntity {

    private Long pubId;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp pubTime;
    private ArticleType type;
    private Draft draft;
    private Category category;
    private Integer commentariesCount = 0;

    public Article() {}

    public Article(Long pubId, ArticleType type, Draft draft, Category category) {
        this.pubId = pubId;
        this.type = type;
        this.draft = draft;
        this.category = category;
    }

    public Long getPubId() {
        return pubId;
    }

    public void setPubId(Long pubId) {
        this.pubId = pubId;
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
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
        return "Article{" +
                "id=" + id +
                ", pubId=" + pubId +
                ", pubTime=" + pubTime +
                ", type=" + type +
                ", draft=" + draft +
                ", category=" + category +
                '}';
    }
}
