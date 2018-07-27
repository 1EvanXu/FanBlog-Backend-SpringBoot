package com.evan.blog.model;

import java.sql.Timestamp;

public class Commentary extends BlogEntity {

    private User commentator;
    private Integer commentatorId;
    private String content;
    private Timestamp commentTime;
    private Commentary parent;
    private Integer parentId;
    private Commentary replyTo;
    private Integer replyToId;
    private Integer belongedPublishedArticle;

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    public Integer getCommentatorId() {
        return commentatorId;
    }

    public void setCommentatorId(Integer commentatorId) {
        this.commentatorId = commentatorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    public Commentary getParent() {
        return parent;
    }

    public void setParent(Commentary parent) {
        this.parent = parent;
    }

    public Commentary getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Commentary replyTo) {
        this.replyTo = replyTo;
    }

    public Integer getBelongedPublishedArticle() {
        return belongedPublishedArticle;
    }

    public void setBelongedPublishedArticle(Integer belongedPublishedArticle) {
        this.belongedPublishedArticle = belongedPublishedArticle;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getReplyToId() {
        return replyToId;
    }

    public void setReplyToId(Integer replyToId) {
        this.replyToId = replyToId;
    }


    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", commentator=" + commentator +
                ", commentatorId=" + commentatorId +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                ", parentId=" + parentId +
                ", replyToId=" + replyToId +
                ", belongedPublishedArticle=" + belongedPublishedArticle +
                '}';
    }
}
