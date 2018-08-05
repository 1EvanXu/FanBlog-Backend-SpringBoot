package com.evan.blog.service;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;
import com.github.pagehelper.PageInfo;

public interface CommentaryService {
    PageInfo<Commentary> getCommentaryByPubId(Integer pubId, Integer pageIndex);
    void postComment(Comment comment);
}
