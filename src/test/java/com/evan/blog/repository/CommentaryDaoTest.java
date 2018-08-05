package com.evan.blog.repository;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;
import com.evan.blog.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        Comment comment = new Comment(
                11,
                null,
                2,
                "something wrong",
                180721499
        );
        commentaryDao.insertCommentary(comment);
    }

    @Test
    public void selectCommentariesByPubId() {
        PageHelper.startPage(1, 6);
        List<Commentary> commentaries = commentaryDao.selectCommentariesByPubId(180711661);
        PageInfo<Commentary> commentaryPageInfo = new PageInfo<>(commentaries);
        for (Commentary commentary: commentaries) {
            System.out.println(commentary);
        }
    }

    @Test
    public void selectCommentariesCountByPubId() {
        Integer count = commentaryDao.selectCommentariesCountByPubId(180711661);
        System.out.println(count);
    }

    @Test
    public void selectCommentariesByParentId() {
        List<Commentary> commentaries = commentaryDao.selectCommentariesByParentId(7);
        for (Commentary commentary: commentaries) {
            System.out.println(commentary);
        }
    }

    @Test
    public void selectCommentariesById() {
        Commentary commentary = commentaryDao.selectCommentaryById(3);
    }
}