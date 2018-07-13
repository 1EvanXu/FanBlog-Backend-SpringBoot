package com.evan.blog.domain;


import com.evan.blog.domain.states.UserLevel;

public class User {
    private Integer id;
    private String name;
    private String avatarImagePath;
    private String password;
    private String email;
    private UserLevel level;

    public User() { }

    public User(String name, String avatarImagePath, String password, String email, UserLevel level) {
        this.name = name;
        this.avatarImagePath = avatarImagePath;
        this.password = password;
        this.email = email;
        this.level = level;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarImagePath() {
        return avatarImagePath;
    }

    public void setAvatarImagePath(String avatarImagePath) {
        this.avatarImagePath = avatarImagePath;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", avatarImagePath='" + avatarImagePath + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", level=" + level +
                '}';
    }
}
