package com.evan.blog.service.impls;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.model.PublishedArticleItem;
import com.evan.blog.service.interfaces.PublishedArticleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishedArticleServiceImpTest {

    @Autowired
    PublishedArticleService publishedArticleService;

    @Test
    public void getAllPublishedArticles() {
        PageInfo<PublishedArticle> allPublishedArticles = publishedArticleService.getAllPublishedArticles(1);
        for (PublishedArticle p: allPublishedArticles.getList()) {
            System.out.println(p);
        }
    }
}