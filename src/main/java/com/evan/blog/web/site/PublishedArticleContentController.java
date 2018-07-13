package com.evan.blog.web.site;

import com.evan.blog.domain.Article;
import com.evan.blog.service.interfaces.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/article")
public class PublishedArticleContentController {

    @Autowired
    ArticleService articleService;

    @GetMapping(path = "/{pubId}")
    private Article getArticlesDetails(
            @PathVariable(name = "pubId") int pubId
    ) {
        Article article = articleService.queryArticleById(pubId);
        return article;
    }
}
