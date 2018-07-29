package com.evan.blog.pojo;

import com.evan.blog.model.PublishedArticle;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"content"})
public class PublishedArticleItem extends PublishedArticleDetails{

    private String articleAbstract;

    public PublishedArticleItem() {}

    public PublishedArticleItem(PublishedArticle publishedArticle) {
        super(publishedArticle);
        this.articleAbstract = content.length() <= 100 ? content : content.substring(0, 100);
    }

    public String getArticleAbstract() {
        return articleAbstract;
    }

    public void setArticleAbstract(String articleAbstract) {
        this.articleAbstract = articleAbstract;
    }

    @Override
    public String toString() {
        return "PublishedArticleItem{" +
                "articleAbstract='" + articleAbstract + '\'' +
                ", pubId=" + pubId +
                ", title='" + title + '\'' +
                ", pubTime=" + pubTime +
                ", type=" + type +
                ", category='" + category + '\'' +
                ", visitorCount=" + visitorCount +
                ", voteCount=" + voteCount +
                ", commentaryCount=" + commentaryCount +
                '}';
    }
}
