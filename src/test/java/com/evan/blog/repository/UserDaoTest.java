package com.evan.blog.repository;

import com.evan.blog.domain.User;
import com.evan.blog.domain.states.UserLevel;
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
        assertNotNull(user);
    }

    @Test
    public void insertUser() {
        User user = new User();
        user.setName("yifanxu");
        user.setAvatarImagePath("/blog/image/avatar/1.png");
        user.setEmail("yifan_xu6@163.com");
        user.setPassword("xuyifan1993fan0601.");
        user.setLevel(UserLevel.Host);
        userDao.insertUser(user);
    }

    @Test
    public void updateUser() {
        User user = userDao.selectUserByEmail("yifan_xu6@163.com");
        user.setAvatarImagePath("/blog/image/avatar/yifanxu.png");
        userDao.updateUser(user);
    }
}