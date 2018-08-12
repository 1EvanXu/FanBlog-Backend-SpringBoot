package com.evan.blog.service.impls;
import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Service;

@Service(value = "publishedArticleCacheService")
public class PublishedArticleCacheServiceImp implements PublishedArticleCacheService {

    private final String votedPrefix = "voted:";

    private final String rankBoard = "pub_articles_rank:";

    private final String latest = "latest_pub_articles:";

    private final String avrPrefix = "article_visitors_record:";

    @Autowired
    RedisOperator redisOperator;
    @Autowired
    PublishedArticleService publishedArticleService;

    @Override
    public boolean vote(Integer pubId, String ip) {
        String key = votedPrefix + pubId;
        updateArticlesRank(pubId);
        return redisOperator.zadd(key, ip, (double)System.currentTimeMillis());
    }

    @Override
    public boolean hasVoted(Integer pubId, String ip) {
        String key = votedPrefix + pubId;
        return null != redisOperator.zrank(key, ip);
    }

    @Override
    public Long getVoteCount(Integer pubId) {
        String key = votedPrefix + pubId;
        Long voteCount = 0L;
        try {
            voteCount = redisOperator.zcard(key);
        } catch (RedisSystemException e) {
            throw e;
        } finally {
            return voteCount;
        }
    }

    @Override
    public Long[] bulkGetVoteCount(Integer[] pubIds) {
        String[] keys = new String[pubIds.length];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = votedPrefix + pubIds[i];
        }
        return redisOperator.pipezcard(keys);
    }

    @Override
    public Long getArticleVisitorCount(Integer pubId) {
        String key = avrPrefix + pubId;
        Long articleVisitorCount = 0L;
        try {
            articleVisitorCount = redisOperator.zcard(key);
        } catch (RedisSystemException e) {
            throw e;
        } finally {
            return articleVisitorCount;
        }
    }

    @Override
    public void updateArticlesRank(Integer pubId) {

        String title = publishedArticleService.getTitleByPubId(pubId);

        String key = pubId.toString() + ":" + title;

        redisOperator.zincr(rankBoard, key, 1.0);
    }

    @Override
    public boolean updateLatestPublishedArticle(Integer pubId, String title) {
        String value = pubId + ":" + title;
        try {
            redisOperator.lpushrpop(latest, value, 8L);
        } catch (RedisSystemException e) {
            return false;
        }
        return true;
    }

}
