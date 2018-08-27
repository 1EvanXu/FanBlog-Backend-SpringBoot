package com.evan.blog.service.impls;

import com.evan.blog.model.GithubUser;
import com.evan.blog.model.User;
import com.evan.blog.model.enums.UserLevel;
import com.evan.blog.repository.UserDao;
import com.evan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getUserById(Long userId) {
        return userDao.selectUserById(userId);
    }

    @Override
    public User getUserByGithubId(Long githubId) {
        return userDao.selectUserByGithubId(githubId);
    }

    @Override
    public User getOrAddUser(User user) {
        GithubUser guser1 = (GithubUser) user;
        Long githubId;
        if ((githubId  = guser1.getGithubId()) == null) {
            return null;
        }
        GithubUser guser2 = (GithubUser) userDao.selectUserByGithubId(githubId);

        if (guser2 == null) {
            userDao.insertUser(guser1);
            return guser1;
        }
        return guser2;
    }

    @Override
    public void upgradeUserLevel(Long id, UserLevel level) {

    }
}
