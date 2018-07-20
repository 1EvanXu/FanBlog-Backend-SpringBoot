package com.evan.blog.repository;

import com.evan.blog.model.Commentary;
import com.evan.blog.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentaryDaoTest {

    @Autowired
    CommentaryDao commentaryDao;
    @Autowired
    UserDao userDao;

    @Test
    public void insertCommentary() {
        User user = userDao.selectUserById(3);
        Commentary commentary = new Commentary();
        commentary.setCommentator(user);
        commentary.setContent("reply commentary 2 content");
        commentary.setParentId(1);
        commentary.setReplyToId(2);
        commentary.setBelongedPublishedArticle(180711661);
        commentaryDao.insertCommentary(commentary);
    }

    @Test
    public void selectCommentariesByPubId() {
        List<Commentary> commentaries = commentaryDao.selectCommentariesByPubId(180711661);
        for (Commentary commentary: commentaries) {
            System.out.println(commentary);
        }
    }

    @Test
    public void selectCommentariesByParentId() {
        List<Commentary> commentaries = commentaryDao.selectCommentariesByParentId(1);
    }

    @Test
    public void selectCommentariesById() {
        Commentary commentary = commentaryDao.selectCommentaryById(3);
    }
}