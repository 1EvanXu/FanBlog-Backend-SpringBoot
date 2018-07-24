package com.evan.blog.service;

import com.evan.blog.pojo.VisitorRecord;

import java.util.Map;

public interface PublishedArticleCacheService {
    void vote(Integer pubId);
    Integer getVoteCount(Integer pubId);
    void addVisitorsRecord(Integer pubId, VisitorRecord record);
    Integer getVisitorsCount(Integer pubId);
    void updateArticlesRank(Integer pubId);
    Map<String, String> getAriclesRankBoard();
}
