package com.evan.blog.model.enums;

public enum ArticleType {
    Original("Original"), Reproduced("Reproduced"), Translation("Translation");
    String name;

    ArticleType() {
    }

    ArticleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ArticleType getType(String s) {
        switch (s) {
            case "Original"   : return Original;
            case "Reproduced" : return Reproduced;
            case "Translation": return Translation;
            default: return null;
        }
    }
}
