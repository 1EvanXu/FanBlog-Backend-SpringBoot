package com.evan.blog.service;

import com.evan.blog.model.User;
import com.evan.blog.model.enums.UserLevel;

public interface UserService {
    User getUserById(Long userId);
    User getUserByGithubId(Long githubId);
    User getOrAddUser(User user);
    void upgradeUserLevel(Long id, UserLevel level);
}
