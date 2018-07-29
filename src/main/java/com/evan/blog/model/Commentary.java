package com.evan.blog.model;

import com.fasterxml.jackson.annotation.*;

import java.sql.Timestamp;
import java.util.List;

public class Commentary extends BlogEntity {

    protected User commentator;

    @JsonProperty("commentaryContent")
    protected String content;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    protected Timestamp commentTime;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("parentCommentary")
    protected Integer parentId;

    @JsonIgnore
    private Commentary replyTo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("replyTo")
    private User referenced;

    protected Integer belongedPublishedArticle;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<Commentary> childCommentaries;

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
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


    public List<Commentary> getChildCommentaries() {
        return childCommentaries;
    }

    public void setChildCommentaries(List<Commentary> childCommentaries) {
        this.childCommentaries = childCommentaries;
    }

    public User getReferenced() {
        if (replyTo != null) {
            referenced = replyTo.getCommentator();
        }
        return referenced;
    }

    @Override
    public String toString() {
        return "Commentary {" + '\n' +
                '\t' + "id=" + id + '\n' +
                '\t' + "commentator=" + commentator + '\n' +
                '\t' + "content='" + content + '\n' +
                '\t' + "commentTime=" + commentTime + '\n' +
                '\t' + "parentId=" + parentId + '\n' +
                '\t' + "referenced=" + this.getReferenced() + '\n' +
                '\t' + "belongedPublishedArticle=" + belongedPublishedArticle + '\n' +
                '\t' + "childCommentaries=" + childCommentaries + '\n' +
                '}' + '\n';
    }
}
