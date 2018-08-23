package com.evan.blog.model.enums;

public enum Order {
    Desc("Desc"), Asc("Asc");
    String description;

    Order() { }

    Order(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Order getOrder(String s) {
        return s.equals("Asc") ? Asc : Desc;
    }
}
