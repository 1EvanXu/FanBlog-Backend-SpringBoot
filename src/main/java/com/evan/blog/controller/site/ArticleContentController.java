package com.evan.blog.controller.site;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;
import com.evan.blog.pojo.ArticleDetails;
import com.evan.blog.pojo.ItemCollection;
import com.evan.blog.service.CommentaryService;
import com.evan.blog.service.ArticleCacheService;
import com.evan.blog.service.ArticleService;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.util.IPUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/article")
public class ArticleContentController {

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentaryService commentaryService;
    @Autowired
    ArticleCacheService articleCacheService;

    @GetMapping(path = "/{pubId}")
    public BlogJSONResult getPublishedArticleContent(@PathVariable("pubId") Integer pubId) {
        ArticleDetails articleDetails = articleService.getArticle(pubId);
        return BlogJSONResult.ok(articleDetails);
    }

    @GetMapping(path = "/{pubId}/commentary/p/{pageIndex}")
    public BlogJSONResult getPublishedArticleCommentaries(
            @PathVariable("pubId") Integer pubId,
            @PathVariable("pageIndex") Integer pageIndex) {
        PageInfo<Commentary> commentaryPageInfo = commentaryService.getCommentaryByPubId(pubId, pageIndex);

        return BlogJSONResult.ok(new ItemCollection((int)commentaryPageInfo.getTotal(),
                commentaryPageInfo.getList()));
    }

    @PostMapping(path = "/{pubId}/commentary")
    public BlogJSONResult addCommentaryInPublishedArticle(
            @PathVariable("pubId") Integer pubId,
            @RequestBody Comment comment) {
        comment.setPubId(pubId);
        commentaryService.postComment(comment);
        BlogJSONResult blogJSONResult = BlogJSONResult.ok();
        blogJSONResult.setMsg("Comment succeed!");
        return blogJSONResult;
    }

    @PutMapping(path = "/{pubId}/vote")
    public BlogJSONResult voteForPublishedArticle (@PathVariable("pubId") Long pubId, HttpServletRequest request) {
        String ip = IPUtil.getRealIP(request);

        boolean vote = articleCacheService.vote(pubId, ip);
        System.out.println(ip);

        BlogJSONResult blogJSONResult = BlogJSONResult.ok(vote);
        blogJSONResult.setMsg("Vote succeed!");
        return blogJSONResult;
    }

    @GetMapping(path = "/{pubId}/vote")
    public BlogJSONResult hasVotedForPublishedArticle(@PathVariable("pubId") Long pubId, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
        System.out.println(ip);
        boolean hasVoted = articleCacheService.hasVoted(pubId, ip);
        BlogJSONResult blogJSONResult = BlogJSONResult.ok(hasVoted);
        blogJSONResult.setMsg("Has voted for this article");
        return blogJSONResult;
    }

    /*
    /article/{pubid}/ GET/PUT/POST/DELETE
    /vote?articleId={id} GET
    /vote/ POST
    /commentaries/p/1?articleId={id} GET
    /commentary POST
     */
}
