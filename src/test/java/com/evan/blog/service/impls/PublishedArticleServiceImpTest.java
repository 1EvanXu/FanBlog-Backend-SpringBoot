package com.evan.blog.service.impls;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.pojo.PublishedArticleItem;
import com.evan.blog.service.PublishedArticleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishedArticleServiceImpTest {

    @Autowired
    PublishedArticleService publishedArticleService;

    @Test
    public void getAllPublishedArticles() {
        PageInfo<PublishedArticleItem> allPublishedArticles = publishedArticleService.getAllPublishedArticleItems(1);
        for (PublishedArticleItem p: allPublishedArticles.getList()) {
            System.out.println(p);
        }
    }
    @Test
    public void getPublishedArticleByPubId() {
        System.out.println(publishedArticleService.getPublishedArticle(180711661));
    }
}