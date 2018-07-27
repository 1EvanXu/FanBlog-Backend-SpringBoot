package com.evan.blog.service.impls;

import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "publishedArticleCacheService")
public class PublishedArticleCacheServiceImp implements PublishedArticleCacheService {

    private final String votedPrefix = "voted:";

    @Autowired
    RedisOperator redisOperator;

    @Override
    public boolean vote(Integer pubId) {
        String key = votedPrefix + pubId;
        String ip = "";

        return redisOperator.zadd(key, ip, (double)System.currentTimeMillis());
    }

    @Override
    public Integer getVoteCount(Integer pubId) {
        return null;
    }

    @Override
    public void updateArticlesRank(Integer pubId) {

    }

    @Override
    public Map<String, String> getAriclesRankBoard() {
        return null;
    }

    @Override
    public boolean updateLatestPublishedArticle(Integer pubId) {
        return false;
    }

    @Override
    public Map<String, String> getLatestPubArticleList() {
        return null;
    }
}
