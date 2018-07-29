package com.evan.blog.repository;

import com.evan.blog.model.User;
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
    public void selectUserByEmail() {
        User user = userDao.selectUserByEmail("yifan_xu6@163.com");
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("eleven");
        user.setAvatarImagePath("/blog/image/avatar/3.png");
        user.setEmail("evan.xu1@foxmail.com");
        user.setPassword("xuyifan1993fan0601.");
        user.setLevel(UserLevel.VIP);
        userDao.insertUser(user);
    }

    @Test
    public void updateUser() {
        User user = userDao.selectUserByEmail("yifan_xu6@163.com");
        user.setAvatarImagePath("/blog/image/avatar/yifanxu.png");
        userDao.updateUser(user);
    }

    @Test
    public void selectUserById() {
        User user = userDao.selectUserById(1);
        System.out.println(user);
        assertNotNull(user);
    }

    @Test
    public void selectUserPassword() {
        String userPassword = userDao.selectUserPassword("yifan_xu6@163.com");
        assertEquals("xuyifan1993fan0601.", userPassword);
    }
}