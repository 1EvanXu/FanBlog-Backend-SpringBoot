package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.model.ArticleQueryFilter;
import com.evan.blog.model.enums.Order;
import com.evan.blog.model.enums.ArticleType;
import com.evan.blog.pojo.ArticleItem;
import com.evan.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleServiceImpTest {

    @Autowired
    ArticleService articleService;

    @Test
    public void getAllPublishedArticles() {
        PageInfo<ArticleItem> allPublishedArticles = articleService.getAllArticleItems(1);
        for (ArticleItem p: allPublishedArticles.getList()) {
            System.out.println(p);
        }
    }
    @Test
    public void getPublishedArticleByPubId() {
        System.out.println(articleService.getArticle(180711661));
    }

    @Test
    public void getPublishedArticlesByFilter() {
        ArticleQueryFilter filter1 = new ArticleQueryFilter("pub_time", Order.Desc, ArticleType.Original);
        ArticleQueryFilter filter2 = new ArticleQueryFilter("pub_time", Order.Asc, null);
        PageInfo<Article> articles1 = articleService.getArticlesByFilter(1, filter1);
        PageInfo<Article> articles2 = articleService.getArticlesByFilter(1, filter2);
        articles1.getList().forEach(System.out::println);
        articles2.getList().forEach(System.out::println);
    }
}