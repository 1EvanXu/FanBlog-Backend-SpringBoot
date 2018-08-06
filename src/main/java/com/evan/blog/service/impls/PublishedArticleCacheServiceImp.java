package com.evan.blog.service.impls;

import com.evan.blog.pojo.VisitorRecord;
import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "publishedArticleCacheService")
public class PublishedArticleCacheServiceImp implements PublishedArticleCacheService {

    private final String votedPrefix = "voted:";

    private final String rankBoard = "pub_articles_rank:";

    private final String latest = "latest_pub_articles:";
    @Autowired
    RedisOperator redisOperator;

    @Override
    public boolean vote(Integer pubId, String ip) {
        String key = votedPrefix + pubId;
        return redisOperator.zadd(key, ip, (double)System.currentTimeMillis());
    }

    @Override
    public Long getVoteCount(Integer pubId) {
        String key = votedPrefix + pubId;
        return redisOperator.zcard(key);
    }

    @Override
    public void updateArticlesRank(Integer pubId) {
        redisOperator.zadd(rankBoard, pubId.toString(), 1.0);
    }

    @Override
    public Map<String, String> getArticlesRankBoard() {
        return null;
    }

    @Override
    public boolean updateLatestPublishedArticle(Integer pubId) {
        //pipe
        redisOperator.lpush(latest, pubId.toString());
        redisOperator.rpop(latest);
        return true;
    }

    @Override
    public Map<String, String> getLatestPubArticleList() {
        return null;
    }
}
