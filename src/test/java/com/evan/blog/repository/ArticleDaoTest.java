package com.evan.blog.repository;

import com.evan.blog.model.*;
import com.evan.blog.model.enums.Order;
import com.evan.blog.model.enums.ArticleType;
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
public class ArticleDaoTest {

    @Autowired
    articleDao articleDao;
    @Autowired
    DraftDao draftDao;
    @Autowired
    CategoryDao categoryDao;
    @Resource(name = "pubIdGenerator")
    PubIdGenerator pubIdGenerator;

    @Test
    public void selectAllArticles() {
        QueryFilter filter = new ArticleQueryFilter("pub_time", Order.Asc, ArticleType.Original);
        List<Article> articles = articleDao.selectArticles(filter);
        for (Article article : articles) {
            System.out.println(article.toString());
        }
    }

    @Test
    public void selectArticlesByCategory() {
        List<Article> articles = articleDao.selectArticlesByCategoryId(2);
        for (Article article : articles) {
            System.out.println(article.toString());
        }
    }

    @Test
    public void selectArticleByPubId() {
        Article article = articleDao.selectArticleByPubId(180711661);
        System.out.println(article);
        assertEquals("test updated article title 4", article.getDraft().getTitle());
        assertEquals("Java", article.getCategory().getName());
    }

    @Test
    public void selectArticleTitleByPubId() {
        String title = articleDao.selectArticleTitleByPubId(180721534);
        assertEquals("test article title 5", title);

    }

    @Test
    public void insertArticle() {
        long pubId = pubIdGenerator.generatePubId();
        Draft draft = draftDao.selectDraftById(3);
        Category category = categoryDao.selectCategoryById(2);
        Article article = new Article(
                pubId,
                ArticleType.Reproduced,
                draft,
                category
        );
        articleDao.insertArticle(article);
    }


    @Test
    public void deleteArticle() {
        articleDao.deleteArticle(180711508);
        Article article = articleDao.selectArticleByPubId(180711508);
        assertNull(article);
    }

    @Test
    public void selectCountOfPubArticlesByCategory() {
        Integer count = articleDao.selectCountOfArticlesByCategory(2);
        assertEquals(1, count.intValue());
    }
}