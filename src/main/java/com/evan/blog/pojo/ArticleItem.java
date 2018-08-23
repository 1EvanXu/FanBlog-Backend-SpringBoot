package com.evan.blog.pojo;

import com.evan.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"content"})
public class ArticleItem extends ArticleDetails {

    private String articleAbstract;

    public ArticleItem() {}

    public ArticleItem(Article article) {
        super(article);
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
        return "ArticleItem{" +
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
