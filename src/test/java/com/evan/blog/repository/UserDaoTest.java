package com.evan.blog.repository;

import com.evan.blog.model.GithubUser;
import com.evan.blog.model.enums.UserLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

    @Autowired
    UserDao userDao;


    @Test
    public void selectUserById() {
        GithubUser user = (GithubUser) userDao.selectUserById(8);
        assertEquals("yifanxu", user.getName());
    }

    @Test
    public void selectUserByGithubId() {
        GithubUser user = (GithubUser) userDao.selectUserByGithubId(231412);
        assertEquals("yifanxu", user.getName());
    }

//    @Test
//    public void insertUser() {
//        GithubUser githubUser = new GithubUser(231412L, "yifanxu", "url", "url", "yifanxu@fanblog.com");
//        userDao.insertUser(githubUser);
//    }

    @Test
    public void updateUserLevel() {
        userDao.updateUserLevel(8, UserLevel.Admin);
    }
}