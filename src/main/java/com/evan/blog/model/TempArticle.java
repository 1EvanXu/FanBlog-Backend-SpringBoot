package com.evan.blog.model;

import com.evan.blog.model.enums.ArticleType;

public class TempArticle extends BlogEntity {
    private Long pubId;
    private String title;
    private ArticleType type;
    private Category category;
    private Long draftId;


    public TempArticle() {
    }

    public Long getPubId() {
        return pubId;
    }

    public void setPubId(Long pubId) {
        this.pubId = pubId;
    }

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getDraftId() {
        return draftId;
    }

    public void setDraftId(Long articleId) {
        this.draftId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TempArticle{" +
                "pubId=" + pubId +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", category=" + category +
                ", articleId=" + draftId +
                '}';
    }
}
