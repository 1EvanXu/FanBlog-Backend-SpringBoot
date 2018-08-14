package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.ArticleStatus;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    private int pageSize = 6;

    @Override
    public PageInfo<Article> getArticles(Integer pageIndex, QueryFilter filter) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articles = articleDao.selectAllArticles(filter);
        return new PageInfo<>(articles);
    }

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
    @Transactional
    public void updateArticleStatus(ArticleStatus status, List<Integer> ids) {
        for (Integer id: ids) {
            articleDao.updateArticleStatus(status, id);
        }

    }

    @Override
    @Transactional
    public void removeArticles(List<Integer> articleIds) {
        for (Integer id: articleIds) {
            articleDao.deleteArticle(id);
        }
    }
}
