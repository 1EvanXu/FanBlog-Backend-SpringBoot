package com.evan.blog.model;

import com.evan.blog.model.enums.DraftStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class Draft extends BlogEntity{

    private String title;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdTime;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp latestEditedTime;
    private DraftStatus status = DraftStatus.Editing;
    private String htmlContent;
    private String markdownContent;

    public Draft() {}

    public Draft(
            String title,
            DraftStatus status,
            String htmlContent,
            String markdownContent) {
        this.title = title;
        this.status = status;
        this.htmlContent = htmlContent;
        this.markdownContent = markdownContent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLatestEditedTime() {
        return latestEditedTime;
    }

    public void setLatestEditedTime(Timestamp latestEditedTime) {
        this.latestEditedTime = latestEditedTime;
    }

    public DraftStatus getStatus() {
        return status;
    }

    public void setStatus(DraftStatus status) {
        this.status = status;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getMarkdownContent() {
        return markdownContent;
    }

    public void setMarkdownContent(String markdownContent) {
        this.markdownContent = markdownContent;
    }

    @Override
    public String toString() {
        return "Draft{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdTime=" + createdTime +
                ", latestEditedTime=" + latestEditedTime +
                ", status=" + status +
                ", htmlContent='" + htmlContent + '\'' +
                ", markdownContent='" + markdownContent + '\'' +
                '}';
    }
}
