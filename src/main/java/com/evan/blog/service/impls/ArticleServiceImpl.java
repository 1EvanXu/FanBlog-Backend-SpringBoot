package com.evan.blog.service.impls;

import com.evan.blog.domain.Article;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;
//    @Transactional
    @Override
    public Article queryArticleById(int id) {
        return articleDao.selectArticleById(id);
    }
}
