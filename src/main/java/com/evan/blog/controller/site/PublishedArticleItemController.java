package com.evan.blog.controller.site;

import com.evan.blog.model.Article;
import com.evan.blog.model.PublishedArticle;
import com.evan.blog.service.interfaces.PublishedArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(path = "/articles")
public class PublishedArticleItemController {

    @Autowired
    PublishedArticleService publishedArticleService;

    @GetMapping(path = "/all/p/{pageIndex}")
    private List<PublishedArticle> getAllPublishedArticleItems(
            @PathVariable int pageIndex
    ) {
        PageInfo<PublishedArticle> publishedArticlesPageInfo = publishedArticleService.getAllPublishedArticles(pageIndex);
        List<PublishedArticle> publishedArticles = publishedArticlesPageInfo.getList();
        return publishedArticles;
    }
}
