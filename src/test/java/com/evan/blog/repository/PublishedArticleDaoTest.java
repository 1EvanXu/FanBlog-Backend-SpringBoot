package com.evan.blog.repository;

import com.evan.blog.domain.Article;
import com.evan.blog.domain.Category;
import com.evan.blog.domain.PublishedArticle;
import com.evan.blog.domain.states.PublishedArticleType;
import com.evan.blog.util.PubIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublishedArticleDaoTest {

    @Autowired
    PublishedArticleDao publishedArticleDao;
    @Autowired
    ArticleDao articleDao;
    @Autowired
    CategoryDao categoryDao;

    @Test
    public void selectPublishedArticleByPubId() {
        PublishedArticle publishedArticle = publishedArticleDao.selectPublishedArticleByPubId(180711661);
        assertEquals("test updated article title 4", publishedArticle.getArticle().getTitle());
        assertEquals("Java", publishedArticle.getCategory().getName());
    }

    @Test
    public void insertPublishedArticle() {
        int pubId = PubIdGenerator.generatePubId();
        Article article = articleDao.selectArticleById(3);
        Category category = categoryDao.selectCategoryById(4);
        PublishedArticle publishedArticle = new PublishedArticle(
                pubId,
                PublishedArticleType.Original,
                article,
                category
        );
        publishedArticleDao.insertPublishedArticle(publishedArticle);
    }

    @Test
    public void deletePublishedArticle() {
        publishedArticleDao.deletePublishedArticle(180711508);
        PublishedArticle publishedArticle = publishedArticleDao.selectPublishedArticleByPubId(180711508);
        assertNull(publishedArticle);
    }
}