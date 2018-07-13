package com.evan.blog.domain;

import com.evan.blog.domain.states.PublishedArticleType;

import java.util.Date;

public class PublishedArticleItem {
    private Integer pubId;
    private String title;
    private String articleAbstract;
    private Date pubTime;
    private PublishedArticleType type;
    private Category category;
    private Integer commentaryCount;
}
