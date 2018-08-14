package com.evan.blog.model;

import com.evan.blog.model.enums.PublishedArticleType;

public class PublishingArticle extends BlogEntity {
    private Integer pubId;
    private String title;
    private PublishedArticleType type;
    private Category category;
    private Integer articleId;


    public PublishingArticle() {
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public PublishedArticleType getType() {
        return type;
    }

    public void setType(PublishedArticleType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "PublishingArticle{" +
                "pubId=" + pubId +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", category=" + category +
                ", articleId=" + articleId +
                '}';
    }
}
