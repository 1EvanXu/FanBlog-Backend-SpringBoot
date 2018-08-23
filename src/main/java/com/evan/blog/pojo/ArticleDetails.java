package com.evan.blog.pojo;


import com.evan.blog.model.Article;
import com.evan.blog.model.enums.ArticleType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;

@JsonIgnoreProperties({"commentaryCount"})
public class ArticleDetails {

    Long pubId;
    String title;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    Timestamp pubTime;
    ArticleType type;
    String category;
    String content;
    Integer visitorCount = 0;
    Integer voteCount = 0;
    Integer commentaryCount = 0;

    public ArticleDetails() { }

    public ArticleDetails(Article article) {
        this.pubId = article.getPubId();
        this.title = article.getDraft().getTitle();
        this.content = article.getDraft().getHtmlContent();
        this.pubTime = article.getPubTime();
        this.type = article.getType();
        this.category = article.getCategory().getName();
        this.commentaryCount = article.getCommentariesCount();
    }

    public Long getPubId() {
        return pubId;
    }

    public void setPubId(Long pubId) {
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

    public ArticleType getType() {
        return type;
    }

    public void setType(ArticleType type) {
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
        return "ArticleDetails{" +
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
