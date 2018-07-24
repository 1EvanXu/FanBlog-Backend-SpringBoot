package com.evan.blog.controller.site;

import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.util.BlogJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/article")
public class PublishedArticleContentController {

    @Autowired
    PublishedArticleService publishedArticleService;

    @GetMapping(path = "/{pubId}")
    public BlogJSONResult getPublishedArticleContent(@PathVariable("pubId") Integer pubId) {
        return BlogJSONResult.ok();
    }

    @GetMapping(path = "/{pubId}/commentary")
    public BlogJSONResult getPublishedArticleCommentaries(@PathVariable("pubId") Integer pubId) {
        return BlogJSONResult.ok("Load commentaries success");
    }

    @PostMapping(path = "/{pubId}/commentary")
    public BlogJSONResult addCommentaryInPublishedArticle(@PathVariable("pubId") Integer pubId) {
        return BlogJSONResult.ok("Load commentaries success");
    }
}
