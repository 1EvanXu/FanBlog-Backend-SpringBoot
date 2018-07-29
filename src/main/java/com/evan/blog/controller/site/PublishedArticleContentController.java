package com.evan.blog.controller.site;

import com.evan.blog.model.Commentary;
import com.evan.blog.model.PublishedArticle;
import com.evan.blog.pojo.ItemListData;
import com.evan.blog.pojo.PublishedArticleDetails;
import com.evan.blog.service.CommentaryService;
import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.pojo.BlogJSONResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/article")
public class PublishedArticleContentController {

    @Autowired
    PublishedArticleService publishedArticleService;
    @Autowired
    CommentaryService commentaryService;

    @GetMapping(path = "/{pubId}")
    public BlogJSONResult getPublishedArticleContent(@PathVariable("pubId") Integer pubId) {
        PublishedArticle publishedArticle = publishedArticleService.getPublishedArticle(pubId);
        return BlogJSONResult.ok(new PublishedArticleDetails(publishedArticle));
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
    public BlogJSONResult addCommentaryInPublishedArticle(@PathVariable("pubId") Integer pubId) {
        return BlogJSONResult.ok("Load commentaries success");
    }
}
