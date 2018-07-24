package com.evan.blog.service.impls;

import com.evan.blog.model.Article;
import com.evan.blog.repository.ArticleDao;
import com.evan.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
