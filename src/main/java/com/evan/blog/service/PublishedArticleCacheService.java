package com.evan.blog.service;


public interface PublishedArticleCacheService {
    boolean vote(Integer pubId, String ip);
    boolean hasVoted(Integer pubId, String ip);
    Long getVoteCount(Integer pubId);
    Long[] bulkGetVoteCount(Integer[] pubIds);
    Long getArticleVisitorCount(Integer pubId);
    void updateArticlesRank(Integer pubId);
    boolean updateLatestPublishedArticle(Integer pubId, String title);
}
