package com.evan.blog.service.impls;

import com.evan.blog.pojo.VisitorRecord;
import com.evan.blog.service.VisitorRecordCacheService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;

@Service(value = "visitorRecordCacheService")
public class VisitorRecordCacheServiceImpl implements VisitorRecordCacheService {

    @Autowired
    RedisOperator redisOperator;

    private final String vr = "visitors_records:";

    private final String avr = "article_visitors_record:";

    private final String rd = "region_distributions:";

    private final long[] PRECISIONS = {300000, 7200000, 86400000}; // ５minutes, 2hours, 1 day

    private final String pvc = "pv_counters:";

    private final String pvcPrefix = "pv_count:";

    @Override
    public void recordVisitor(VisitorRecord record) {
        double score = new Long(record.getVisitTime()).doubleValue();
        redisOperator.zadd(vr, record.getRecord(), score);
    }

    @Override
    public Long getVisitorRecordCount() {
        return redisOperator.zcard(vr);
    }

    @Override
    public void updateRegionDistributions(String city) {
        redisOperator.zadd(rd, city, 1.0);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<String>> getRegionDistributions() {
        return redisOperator.zrevrange(rd, 0, 8);
    }

    @Override
    public void addVisitorsRecord(Integer pubId, VisitorRecord record) {
        double score = new Long(record.getVisitTime()).doubleValue();
        redisOperator.zadd(avr + pubId.toString(), record.getArticleVisitRecord(), score);
    }

    @Override
    public Long getVisitorsCount(Integer pubId) {
        return redisOperator.zcard(avr + pubId);
    }

    @Override
    public void pageViewCount() {

        long now = System.currentTimeMillis();
        RedisCallback<List<Object>> redisCallback = new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (long p : PRECISIONS) {
                    String pnow = "" + (now / p) * p; // current time precision;
                    connection.zAdd(pvc.getBytes(), 0.0, (pvcPrefix + p).getBytes());
                    connection.hIncrBy((pvcPrefix + p).getBytes(), pnow.getBytes(), 1.0);
                }
                return connection.closePipeline();
            }
        };
        redisOperator.pipeline(redisCallback);

    }

    @Override
    public Map<Object, Object> getPageViewCount(long precision) {
        for (long p : PRECISIONS) {
            if (precision == p) {
                break;
            }
            throw new InvalidParameterException("Invalid parameter, " + p + " may be valid.");
        }

        return redisOperator.hgetall(pvcPrefix + precision);
    }


}
