package com.evan.blog.repository;

import com.evan.blog.model.Article;
import com.evan.blog.model.Category;
import com.evan.blog.model.PublishedArticle;
import com.evan.blog.model.enums.PublishedArticleType;
import com.evan.blog.util.PubIdGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource(name = "pubIdGenerator")
    PubIdGenerator pubIdGenerator;

    @Test
    public void selectAllPublishedArticles() {
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticles();
        for (PublishedArticle publishedArticle: publishedArticles) {
            System.out.println(publishedArticle.toString());
        }
    }

    @Test
    public void selectPublishedArticlesByCategory() {
        List<PublishedArticle> publishedArticles = publishedArticleDao.selectPublishedArticlesByCategoryId(2);
        for (PublishedArticle publishedArticle: publishedArticles) {
            System.out.println(publishedArticle.toString());
        }
    }

    @Test
    public void selectPublishedArticleByPubId() {
        PublishedArticle publishedArticle = publishedArticleDao.selectPublishedArticleByPubId(180711661);
        System.out.println(publishedArticle);
        assertEquals("test updated article title 4", publishedArticle.getArticle().getTitle());
        assertEquals("Java", publishedArticle.getCategory().getName());
    }

    @Test
    public void selectPublishedArticleTitleByPubId() {
        String title = publishedArticleDao.selectPublishedArticleTitleByPubId(180721534);
        assertEquals("test article title 5", title);

    }

    @Test
    public void insertPublishedArticle() {
        int pubId = pubIdGenerator.generatePubId();
        Article article = articleDao.selectArticleById(23);
        Category category = categoryDao.selectCategoryById(2);
        PublishedArticle publishedArticle = new PublishedArticle(
                pubId,
                PublishedArticleType.Reproduced,
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

    @Test
    public void selectCountOfPubArticlesByCategory() {
        Integer count = publishedArticleDao.selectCountOfPubArticlesByCategory(2);
        assertEquals(1, count.intValue());
    }
}