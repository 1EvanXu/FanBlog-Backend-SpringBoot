package com.evan.blog.repository;

import com.evan.blog.domain.User;

public interface UserDao {
    User selectUserByEmail(String email);
    void insertUser(User user);
    void updateUser(User user);
}
