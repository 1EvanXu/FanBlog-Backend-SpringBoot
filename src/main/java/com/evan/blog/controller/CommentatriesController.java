package com.evan.blog.controller;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemCollection;
import com.evan.blog.service.CommentaryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "apis/commentaries")
public class CommentatriesController {

    @Autowired
    CommentaryService commentaryService;

    @GetMapping(path = "/{pubId}/p/{pageIndex}")
    public BlogJSONResult getCommentaries(
            @PathVariable("pubId") Integer pubId,
            @PathVariable("pageIndex") Integer pageIndex) {
        PageInfo<Commentary> commentaryPageInfo = commentaryService.getCommentaryByPubId(pubId, pageIndex);

        return BlogJSONResult.ok(new ItemCollection((int)commentaryPageInfo.getTotal(),
                commentaryPageInfo.getList()));
    }

    @PostMapping(path = "/{pubId}")
    public BlogJSONResult addCommentary(
            @PathVariable("pubId") Integer pubId,
            @RequestBody Comment c) {

        c.setPubId(pubId);
        commentaryService.postComment(c);

        BlogJSONResult JSONResult = BlogJSONResult.ok();
        JSONResult.setMsg("Comment succeed!");
        return JSONResult;
    }

}
