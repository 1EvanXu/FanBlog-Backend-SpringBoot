package com.evan.blog.domain;

import java.util.Date;

public class Commentary {
    private Integer id;
    private User Commentator;
    private String content;
    private Date commentTime;
    private Commentary parent;
    private Commentary replyTo;
    private PublishedArticle belongedPublishedArticle;

    public User getCommentator() {
        return Commentator;
    }

    public void setCommentator(User commentator) {
        Commentator = commentator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
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

    public PublishedArticle getBelongedPublishedArticle() {
        return belongedPublishedArticle;
    }

    public void setBelongedPublishedArticle(PublishedArticle belongedPublishedArticle) {
        this.belongedPublishedArticle = belongedPublishedArticle;
    }

    @Override
    public String toString() {
        return "Commentary{" +
                "id=" + id +
                ", Commentator=" + Commentator.getId() +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                ", parent=" + parent +
                ", replyTo=" + replyTo +
                ", belongedPublishedArticle=" + belongedPublishedArticle.getId() +
                '}';
    }
}
