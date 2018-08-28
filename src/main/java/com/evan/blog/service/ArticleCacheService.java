package com.evan.blog.service;


public interface ArticleCacheService {
    boolean vote(Long pubId, String ip);
    boolean hasVoted(Long pubId, String ip);
    Long getVoteCount(Long pubId);
    Long[] bulkGetVoteCount(Long[] pubIds);
    Long getArticleVisitorCount(Long pubId);
    Long[] bulkGetArticleVisitorCount(Long[] pubIds);
    void updateArticlesRank(Long pubId);
    void updateLatestPublishedArticle(Long pubId, String title);
    void removePublishedArticleFromCache(String key);
}
