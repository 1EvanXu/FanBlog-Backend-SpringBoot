package com.evan.blog.repository;

import com.evan.blog.model.Commentary;

import java.util.List;

public interface CommentaryDao {
    void insertCommentary(Commentary commentary);
    List<Commentary> selectCommentariesByPubId(Integer pubId);
    Integer selectCommentariesCountByPubId(Integer pubId);
    List<Commentary> selectCommentariesByParentId(Integer parentId);
    Commentary selectCommentaryById(Integer id);
}
