package com.evan.blog.service.impls;
import com.evan.blog.service.PublishedArticleCacheService;
import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Service;

import java.util.List;

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
        
        String key = pubId.toString();

        redisOperator.zincr(rankBoard, key, 1.0);
    }

    @Override
    public boolean updateLatestPublishedArticle(Integer pubId, String title) {
        String value = pubId.toString();
        try {
            redisOperator.lpushrpop(latest, value, 8L);
        } catch (RedisSystemException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean removePublishedArticleFromCache(String key) {
//        RedisCallback<List<Object>> redisCallback = new RedisCallback<List<Object>>() {
//            @Override
//            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
//                String[] strings = key.split(":");
//                Integer pubId = Integer.parseInt(strings[0]);
//                connection.openPipeline();
//                connection.zRem(rankBoard.getBytes(), key.getBytes());
//                connection.lRem(latest.getBytes(), 1L, key.getBytes());
//                connection.del((votedPrefix+pubId.toString()).getBytes());
//                connection.del((avrPrefix+pubId.toString()).getBytes());
//                return connection.closePipeline();
//            }
//        };
        RedisCallback<List<Object>> redisCallback = new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                String[] strings = key.split(":");
                Integer pubId = Integer.parseInt(strings[0]);
                connection.openPipeline();
//                connection.zScore(rankBoard.getBytes(), key.getBytes());
//                connection.lRem(latest.getBytes(), 0L, key.getBytes());
                connection.get((votedPrefix+pubId.toString()).getBytes());
                connection.get((avrPrefix+pubId.toString()).getBytes());
                return connection.closePipeline();
            }
        };
        try {

            List<Object> pipeline = redisOperator.pipeline(redisCallback);
            pipeline.forEach(i -> System.out.println("---" + i));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
