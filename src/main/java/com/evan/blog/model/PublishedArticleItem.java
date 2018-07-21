package com.evan.blog.model;

import com.evan.blog.model.enums.PublishedArticleType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.Date;

public class PublishedArticleItem {
    private Integer pubId;
    private String title;
    private String articleAbstract;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp pubTime;
    private PublishedArticleType type;
    private String category;
    private Integer visitorCount = 0;
    private Integer voteCount = 0;
    private Integer commentaryCount;

    public PublishedArticleItem() {}

    public PublishedArticleItem(PublishedArticle publishedArticle) {
        this.pubId = publishedArticle.getPubId();
        this.title = publishedArticle.getArticle().getTitle();
        this.setArticleAbstract(publishedArticle.getArticle().getMarkdownContent());
        this.pubTime = publishedArticle.getPubTime();
        this.type = publishedArticle.getType();
        this.category = publishedArticle.getCategory().getName();
        this.commentaryCount = publishedArticle.getCommentariesCount();
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        if (articleAbstract != null) {
            this.articleAbstract = articleAbstract.length() < 100 ? articleAbstract : articleAbstract.substring(0, 100);
        }
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCommentaryCount() {
        return commentaryCount;
    }

    public void setCommentaryCount(Integer commentaryCount) {
        this.commentaryCount = commentaryCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    @Override
    public String toString() {
        return "PublishedArticleItem{" +
                "pubId=" + pubId +
                ", title='" + title + '\'' +
                ", articleAbstract='" + articleAbstract + '\'' +
                ", pubTime=" + pubTime +
                ", type=" + type +
                ", category='" + category + '\'' +
                ", visitorCount=" + visitorCount +
                ", voteCount=" + voteCount +
                ", commentaryCount=" + commentaryCount +
                '}';
    }
}
