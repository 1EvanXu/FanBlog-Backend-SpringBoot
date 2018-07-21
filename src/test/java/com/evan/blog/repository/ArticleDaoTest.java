package com.evan.blog.repository;

import com.evan.blog.model.Article;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.model.enums.Order;
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
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void getAllArticlesByCreatedTime() {
        QueryFilter queryFilter = new QueryFilter("created_time", Order.Asc, ArticleStatus.Editing);
        PageHelper.startPage(0, 1);
        List<Article> articles = articleDao.selectAllArticles(queryFilter);
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        for (Article article : articles) {
            System.out.println(article);
        }
        assertEquals(2, pageInfo.getTotal());
    }

    @Test
    public void selectArticleById() {
        Article article = articleDao.selectArticleById(1);
        String expectedArticleTitle = "test article title 1";
        assertEquals(expectedArticleTitle, article.getTitle());
    }

    @Test
    public void insertArticle() {
        int i = articleDao.selectArticlesCount();
        Article article = new Article(
                "test article title " + i,
                ArticleStatus.Editing,
                "<h1>content title " + i + "</h1>",
                "#content title " + i
        );
        articleDao.insertArticle(article);
        assertEquals(i + 1, article.getId().intValue());
    }
    

    @Test
    public void updateArticleStatus() {
        articleDao.updateArticleStatus(ArticleStatus.Deleted, 3);
        Article article = articleDao.selectArticleById(3);
        assertEquals(ArticleStatus.Deleted, article.getStatus());
    }

    @Test
    public void updateArticle() {
        String updatedTitle = "test updated article title 4";
        Article article = new Article();
        article.setId(4);
        article.setTitle(updatedTitle);
        articleDao.updateArticle(article);
        Article afterUpdatedArticle = articleDao.selectArticleById(4);
        assertEquals(updatedTitle, afterUpdatedArticle.getTitle());
    }

    @Test
    public void deleteArticle() {

        articleDao.deleteArticle(6);
        Article article = articleDao.selectArticleById(6);
        assertNull(article);
    }
}