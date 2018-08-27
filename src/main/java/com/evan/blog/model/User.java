package com.evan.blog.model;


import com.evan.blog.model.enums.UserLevel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;

public class User extends BlogEntity {

    private UserLevel level = UserLevel.Common;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp registTime;

    public User() { }

    public User(UserLevel userLevel) { this.level = userLevel; }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }

    public Timestamp getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Timestamp registTime) {
        this.registTime = registTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", level=" + level +
                '}';
    }
}
