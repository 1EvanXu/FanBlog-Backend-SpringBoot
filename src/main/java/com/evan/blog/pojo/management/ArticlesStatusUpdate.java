package com.evan.blog.pojo.management;

import com.evan.blog.model.enums.ArticleStatus;

import java.util.List;

public class ArticlesStatusUpdate {
    private List<Integer> articleIds;
    private ArticleStatus status;

    public ArticlesStatusUpdate() {
    }

    public List<Integer> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Integer> articleIds) {
        this.articleIds = articleIds;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public void setStatus(ArticleStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ArticlesStatusUpdate{" +
                "articleIds=" + articleIds +
                ", status=" + status +
                '}';
    }
}
