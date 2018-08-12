package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article queryArticleById(int id) {
        return articleDao.selectArticleById(id);
    }

    @Override
    public void addArticle(Article article) {
        articleDao.insertArticle(article);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

    @Override
    public void updateArticleStatus(ArticleStatus status, int id) {

    }

    @Override
    public void removeArticle(int id) {

    }
}
