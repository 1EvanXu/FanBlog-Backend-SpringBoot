package com.evan.blog.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class SideInfoItem {
    private Integer id;
    private String title;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Integer number;

    public SideInfoItem() {
    }

    public SideInfoItem(Integer id, String title, Integer number) {
        this.id = id;
        this.title = title;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "SideInfoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", number=" + number +
                '}';
    }
}
