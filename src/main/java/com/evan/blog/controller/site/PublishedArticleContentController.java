package com.evan.blog.controller.site;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;
import com.evan.blog.model.PublishedArticle;
import com.evan.blog.pojo.ItemListData;
import com.evan.blog.pojo.PublishedArticleDetails;
import com.evan.blog.service.CommentaryService;
import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.pojo.BlogJSONResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(path = "/article")
public class PublishedArticleContentController {

    @Autowired
    PublishedArticleService publishedArticleService;
    @Autowired
    CommentaryService commentaryService;
    @Autowired
    PublishedArticleCacheService publishedArticleCacheService;

    @GetMapping(path = "/{pubId}")
    public BlogJSONResult getPublishedArticleContent(@PathVariable("pubId") Integer pubId) {
        PublishedArticleDetails publishedArticleDetails = publishedArticleService.getPublishedArticle(pubId);
        return BlogJSONResult.ok(publishedArticleDetails);
    }

    @GetMapping(path = "/{pubId}/commentary/p/{pageIndex}")
    public BlogJSONResult getPublishedArticleCommentaries(
            @PathVariable("pubId") Integer pubId,
            @PathVariable("pageIndex") Integer pageIndex) {
        PageInfo<Commentary> commentaryPageInfo = commentaryService.getCommentaryByPubId(pubId, pageIndex);

        return BlogJSONResult.ok(new ItemListData((int)commentaryPageInfo.getTotal(),
                commentaryPageInfo.getList()));
    }

    @PostMapping(path = "/{pubId}/commentary")
    public BlogJSONResult addCommentaryInPublishedArticle(
            @PathVariable("pubId") Integer pubId,
            @RequestBody Comment comment) {
        comment.setPubId(pubId);
        commentaryService.postComment(comment);
        return BlogJSONResult.ok("Comment succeed!");
    }

    @PutMapping(path = "/{pubId}/vote")
    public BlogJSONResult voteForPublishedArticle (@PathVariable("pubId") Integer pubId, HttpServletRequest request) {
        String ip = request.getRemoteAddr();
//        HttpSession session = request.getSession();
        publishedArticleCacheService.vote(pubId, ip);
        System.out.println(ip);
        return BlogJSONResult.ok("Vote succeed!");
    }
}
