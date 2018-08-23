package com.evan.blog.pojo.management;

import com.evan.blog.model.enums.DraftStatus;

import java.util.List;

public class DraftStatusUpdate {
    private List<Long> articleIds;
    private DraftStatus status;

    public DraftStatusUpdate() {
    }

    public List<Long> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Long> articleIds) {
        this.articleIds = articleIds;
    }

    public DraftStatus getStatus() {
        return status;
    }

    public void setStatus(DraftStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DraftStatusUpdate{" +
                "articleIds=" + articleIds +
                ", status=" + status +
                '}';
    }
}
