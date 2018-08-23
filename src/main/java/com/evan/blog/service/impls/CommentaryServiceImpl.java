package com.evan.blog.service.impls;

import com.evan.blog.model.Comment;
import com.evan.blog.model.Commentary;
import com.evan.blog.repository.CommentaryDao;
import com.evan.blog.service.CommentaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "commentaryService")
public class CommentaryServiceImpl implements CommentaryService {

    private int pageSize = 6;

    @Autowired
    CommentaryDao commentaryDao;

    @Override
    public PageInfo<Commentary> getCommentaryByPubId(Integer pubId, Integer pageIndex) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Commentary> commentaries = commentaryDao.selectCommentariesByPubId(pubId);
        return new PageInfo<>(commentaries);
    }

    @Override
    public void postComment(Comment comment) {
        commentaryDao.insertCommentary(comment);
    }
}
