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
        GithubUser user = (GithubUser) userDao.selectUserById(9);
        assertEquals("1EvanXu", user.getName());
    }

    @Test
    public void selectUserByGithubId() {
        GithubUser user = (GithubUser) userDao.selectUserByGithubId(26501223);
        assertEquals("1EvanXu", user.getName());
    }

    @Test
    public void updateUserLevel() {
        userDao.updateUserLevel(9, UserLevel.Admin);
    }
}