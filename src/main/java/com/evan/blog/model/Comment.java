package com.evan.blog.model;

public class Comment {
    private Integer parent;

    private Integer replyTo;

    private Integer commentator;

    private String content;

    private Integer pubId;

    public Comment() { }

    public Comment(Integer parent, Integer replyTo, Integer commentator, String content, Integer pubId) {
        this.parent = parent;
        this.replyTo = replyTo;
        this.commentator = commentator;
        this.content = content;
        this.pubId = pubId;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(Integer replyTo) {
        this.replyTo = replyTo;
    }

    public Integer getCommentator() {
        return commentator;
    }

    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPubId() {
        return pubId;
    }

    public void setPubId(Integer pubId) {
        this.pubId = pubId;
    }

    @Override
    public String toString() {
        return "Comment {" + '\n' +
                '\t' + "parent=" + parent + '\n' +
                '\t' + ", replyTo=" + replyTo + '\n' +
                '\t' + ", commentator=" + commentator + '\n' +
                '\t' + ", content='" + content + '\n' +
                '\t' + ", pubId=" + pubId + '\n' +
                '}' + '\n';
    }
}
