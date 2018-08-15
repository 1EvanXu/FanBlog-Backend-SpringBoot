package com.evan.blog.model.enums;

public enum ArticleStatus {
    Deleted("Deleted", -1), Editing("Editing", 0), Published("Published", 1);

    private String name;
    private int statusCode;

    ArticleStatus(String name, int statusCode) {
        this.name = name;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public static ArticleStatus getArticleStatus(int code) {
        switch (code) {
            case -1: return Deleted;
            case 0: return Editing;
            case 1: return Published;
            default: return null;
        }
    }

    public static ArticleStatus getArticleStatus(String s) {
        switch (s) {
            case "Deleted"   : return Deleted;
            case "Editing"   : return Editing;
            case "Published" : return Published;
            default: return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
