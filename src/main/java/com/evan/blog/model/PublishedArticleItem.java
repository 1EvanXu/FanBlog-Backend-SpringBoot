package com.evan.blog.model;

import com.evan.blog.model.enums.PublishedArticleType;

import java.util.Date;

public class PublishedArticleItem {
    private Integer pubId;
    private String title;
    private String articleAbstract;
    private Date pubTime;
    private PublishedArticleType type;
    private Category category;
    private Integer commentaryCount;

    public PublishedArticleItem(PublishedArticle publishedArticle) {
        this.pubId = publishedArticle.getPubId();
        this.title = publishedArticle.getArticle().getTitle();
        this.articleAbstract = publishedArticle.getArticle().getMarkdownContent().substring(0, 300);
        this.type = publishedArticle.getType();
    }
}
