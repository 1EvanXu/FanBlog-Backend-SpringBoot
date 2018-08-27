package com.evan.blog.pojo.authorization;

import com.evan.blog.model.GithubUser;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubUserResponse {

    private Long id;
    @JsonProperty("login")
    private String name;
    @JsonProperty("avatar_url")
    private String avatarUrl;
    @JsonProperty("html_url")
    private String htmlUrl;
    private String email;

    public GithubUserResponse() {
    }

    public GithubUserResponse(Long id, String name, String avatarUrl, String htmlUrl, String email) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public GithubUser convertToGithubUser() {
        return new GithubUser(id, name, avatarUrl, htmlUrl, email);
    }

    public boolean validResponse() {
        return id != null && name != null && !name.equals("");
    }
}
