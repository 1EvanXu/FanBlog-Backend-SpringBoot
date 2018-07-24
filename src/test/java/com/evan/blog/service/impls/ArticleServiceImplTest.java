package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImplTest {

    @Autowired
    ArticleService service;

    @Test
    public void queryArticleById() {
        Article article = service.queryArticleById(1);
        assertEquals("test article title 1", article.getTitle());
    }
}