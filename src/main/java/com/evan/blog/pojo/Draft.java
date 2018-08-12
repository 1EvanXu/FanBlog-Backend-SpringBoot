package com.evan.blog.pojo;

import com.evan.blog.model.Article;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"htmlContent", "createdTime", "latestEditedTime", "status"})
public class Draft extends Article {
    private Long tempArticleId;

    public Long getTempArticleId() {
        return tempArticleId;
    }

    public void setTempAticleId(Long tempArticleId) {
        this.tempArticleId = tempArticleId;
    }
}
