package com.evan.blog.repository;

import com.evan.blog.domain.Article;
import com.evan.blog.domain.states.ArticleStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleDaoTest {

    @Autowired
    private ArticleDao articleDao;

    @Test
    public void getAllArticlesByCreatedTime() {
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