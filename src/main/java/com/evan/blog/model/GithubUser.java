package com.evan.blog.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"registTime", "email"})
public class GithubUser extends User {
    private Long githubId;
    private String name;
    private String avatarUrl;
    private String githubHomePage;
    private String email;

    public GithubUser() {
    }

    public GithubUser(Long githubId, String name, String avatarUrl, String githubHomePage, String email) {
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.githubHomePage = githubHomePage;
        this.email = email;
        this.githubId = githubId;
    }

    public Long getGithubId() {
        return githubId;
    }

    public void setGithubId(Long githubId) {
        this.githubId = githubId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getGithubHomePage() {
        return githubHomePage;
    }

    public void setGithubHomePage(String githubHomePage) {
        this.githubHomePage = githubHomePage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", githubHomePage='" + githubHomePage + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
