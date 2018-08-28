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
    ArticleDao ArticleDao;
    @Autowired
    DraftDao draftDao;
    @Autowired
    CategoryDao categoryDao;

    @Test
    public void selectAllArticles() {
        QueryFilter filter = new ArticleQueryFilter("pub_time", Order.Asc, ArticleType.Original);
        List<Article> articles = ArticleDao.selectArticles(filter);
        for (Article article : articles) {
            assertEquals(ArticleType.Original, article.getType());
        }
        Article article1 = articles.get(0);
        Article article2 = articles.get(1);
        int r = article1.getPubTime().compareTo(article2.getPubTime());
        assertTrue(r < 0);
    }

    @Test
    public void selectArticlesByCategory() {
        ArticleDao.selectArticlesByCategoryId(2);
    }

    @Test
    public void selectArticleByPubId() {
        ArticleDao.selectArticleByPubId(180711661);
    }

    @Test
    public void selectArticleTitleByPubId() {
        ArticleDao.selectArticleTitleByPubId(180721534);
    }

//    @Test
//    public void insertArticle() {
//        long pubId = pubIdGenerator.generatePubId();
//        Draft draft = draftDao.selectDraftById(3);
//        Category category = categoryDao.selectCategoryById(2);
//        Article article = new Article(
//                pubId,
//                ArticleType.Reproduced,
//                draft,
//                category
//        );
//        ArticleDao.insertArticle(article);
//    }


//    @Test
//    public void deleteArticle() {
//        ArticleDao.deleteArticle(180711508);
//        Article article = ArticleDao.selectArticleByPubId(180711508);
//        assertNull(article);
//    }

    @Test
    public void selectCountOfPubArticlesByCategory() {
        ArticleDao.selectCountOfArticlesByCategory(2);
    }
}