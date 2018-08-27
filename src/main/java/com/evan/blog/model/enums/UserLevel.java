package com.evan.blog.model.enums;

public enum UserLevel {
    Admin("Admin", 3), VIP("VIP", 1), Common("Common", 0);

    private String name;

    private int levelcode;

    UserLevel(String name, int levelcode) {
        this.name = name;
        this.levelcode = levelcode;
    }

    public int getLevelcode() {
        return levelcode;
    }

    public static UserLevel getUserLevel(int levelcode) {
        switch (levelcode) {
            case 3: return Admin;
            case 1: return VIP;
            case 0: return Common;
            default: return null;
        }
    }

    @Override
    public String toString() {
        return name + "(" + levelcode +  ")";
    }
}
