package com.evan.blog.repository;

import com.evan.blog.model.User;
import com.evan.blog.model.enums.UserLevel;
import org.apache.ibatis.annotations.Param;

import javax.validation.constraints.NotNull;

public interface UserDao {
    User selectUserById(long id);
    User selectUserByGithubId(long githubId);
    void insertUser(@NotNull User user);
    void updateUserLevel(@Param("id") @NotNull long id, @Param("level") UserLevel level);
}
