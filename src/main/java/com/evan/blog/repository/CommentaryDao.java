package com.evan.blog.repository;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;

import java.util.List;

public interface CommentaryDao {
    void insertCommentary(Comment comment);
    List<Commentary> selectCommentariesByPubId(long pubId);
    int selectCommentariesCountByPubId(long pubId);
    List<Commentary> selectCommentariesByParentId(long parentId);
    Commentary selectCommentaryById(long id);
    void deleteCommentariesByPubId(long pubId);
}
