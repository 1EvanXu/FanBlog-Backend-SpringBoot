package com.evan.blog.pojo;


import com.evan.blog.model.PublishedArticle;
import com.evan.blog.model.enums.PublishedArticleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties({"commentaryCount"})
public class PublishedArticleDetails {

    Integer pubId;
    String title;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    Timestamp pubTime;
    PublishedArticleType type;
    String category;
    String content;
    Integer visitorCount = 0;
    Integer voteCount = 0;
    Integer commentaryCount = 0;

    public PublishedArticleDetails () { }

    public PublishedArticleDetails (PublishedArticle publishedArticle) {
        this.pubId = publishedArticle.getPubId();
        this.title = publishedArticle.getArticle().getTitle();
        this.content = publishedArticle.getArticle().getHtmlContent();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getVisitorCount() {
        return visitorCount;
    }

    public void setVisitorCount(Integer visitorCount) {
        this.visitorCount = visitorCount;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public Integer getCommentaryCount() {
        return commentaryCount;
    }

    public void setCommentaryCount(Integer commentaryCount) {
        this.commentaryCount = commentaryCount;
    }

    @Override
    public String toString() {
        return "PublishedArticleDetails{" +
                "pubId=" + pubId +
                ", title='" + title + '\'' +
                ", pubTime=" + pubTime +
                ", type=" + type +
                ", category='" + category + '\'' +
                ", content='" + content + '\'' +
                ", visitorCount=" + visitorCount +
                ", voteCount=" + voteCount +
                ", commentaryCount=" + commentaryCount +
                '}';
    }
}
