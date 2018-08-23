package com.evan.blog.controller;

import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.service.ArticleCacheService;
import com.evan.blog.util.IPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/apis/cache/article")
public class ArticleCacheController {

    @Autowired
    ArticleCacheService articleCacheService;

    @PostMapping(path = "/{pubId}/vote")
    public BlogJSONResult voteForPublishedArticle (@PathVariable("pubId") Long pubId, HttpServletRequest request) {
        String ip = IPUtil.getRealIP(request);

        boolean vote = articleCacheService.vote(pubId, ip);

        BlogJSONResult blogJSONResult = BlogJSONResult.ok(vote);
        blogJSONResult.setMsg("Vote succeed!");
        return blogJSONResult;
    }

    @GetMapping(path = "/{pubId}/vote")
    public BlogJSONResult hasVotedForPublishedArticle(@PathVariable("pubId") Long pubId, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        boolean hasVoted = articleCacheService.hasVoted(pubId, ip);
        BlogJSONResult blogJSONResult = BlogJSONResult.ok(hasVoted);
        blogJSONResult.setMsg("Has voted for this article");
        return blogJSONResult;
    }
}
