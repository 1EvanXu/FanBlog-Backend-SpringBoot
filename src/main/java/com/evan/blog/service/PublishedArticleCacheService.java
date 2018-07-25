package com.evan.blog.service;

import com.evan.blog.pojo.VisitorRecord;

import java.util.Map;

public interface PublishedArticleCacheService {
    boolean vote(Integer pubId);
    Integer getVoteCount(Integer pubId);
    void updateArticlesRank(Integer pubId);
    Map<String, String> getAriclesRankBoard();
}
