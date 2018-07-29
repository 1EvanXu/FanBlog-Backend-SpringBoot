package com.evan.blog.repository;

import com.evan.blog.model.User;

public interface UserDao {
    User selectUserById(Integer id);
    User selectUserByEmail(String email);
    String selectUserPassword(String email);
    void insertUser(User user);
    void updateUser(User user);
}
