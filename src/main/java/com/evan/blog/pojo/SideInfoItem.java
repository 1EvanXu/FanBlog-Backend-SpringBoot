package com.evan.blog.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class SideInfoItem {
    private Long id;
    private String title;
    @JsonIgnoreProperties(ignoreUnknown = true)
    private Integer number;

    public SideInfoItem() {
    }

    public SideInfoItem(Long id, String title, Integer number) {
        this.id = id;
        this.title = title;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
