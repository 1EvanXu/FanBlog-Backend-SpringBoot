package com.evan.blog.domain;

import com.evan.blog.domain.states.ArticleStatus;
import java.sql.Timestamp;

public class Article {
    private Integer id;
    private String title;
    private Timestamp createdTime;
    private Timestamp latestModifiedTime;
    private ArticleStatus status;
    private String htmlContent;
    private String markdownContent;

    public Article() {}

    public Article(
            String title,
            ArticleStatus status,
            String htmlContent,
            String markdownContent) {
        this.title = title;
        this.status = status;
        this.htmlContent = htmlContent;
        this.markdownContent = markdownContent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Timestamp getLatestModifiedTime() {
        return latestModifiedTime;
    }

    public void setLatestModifiedTime(Timestamp latestModifiedTime) {
        this.latestModifiedTime = latestModifiedTime;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
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
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdTime=" + createdTime +
                ", latestModifiedTime=" + latestModifiedTime +
                ", status=" + status +
                ", HtmlContent='" + htmlContent + '\'' +
                ", MarkdownContent='" + markdownContent + '\'' +
                '}';
    }
}
