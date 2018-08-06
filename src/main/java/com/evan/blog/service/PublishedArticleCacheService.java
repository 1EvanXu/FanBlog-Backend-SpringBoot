package com.evan.blog.service;

import java.util.Map;

public interface PublishedArticleCacheService {
    boolean vote(Integer pubId, String ip);
    Long getVoteCount(Integer pubId);
    void updateArticlesRank(Integer pubId);
    Map<String, String> getArticlesRankBoard();
    boolean updateLatestPublishedArticle(Integer pubId);
    Map<String, String> getLatestPubArticleList();
}
