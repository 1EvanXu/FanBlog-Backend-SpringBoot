package com.evan.blog.service.impls;

import com.evan.blog.model.Commentary;
import com.evan.blog.service.CommentaryService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentaryServiceImplTest {

    @Autowired
    CommentaryService commentaryService;

    @Test
    public void getCommentaryByPubId() {
        PageInfo<Commentary> commentaryPageInfo =
                commentaryService.getCommentaryByPubId(180711661, 1);
        for (Commentary c: commentaryPageInfo.getList()) {
            System.out.println(c);
        }
    }
}