package com.evan.blog.service.impls;

import com.evan.blog.pojo.VisitorRecord;
import com.evan.blog.service.VisitorRecordCacheService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

@Service(value = "visitorRecordCacheService")
public class VisitorRecordCacheServiceImpl implements VisitorRecordCacheService {

    @Autowired
    RedisOperator redisOperator;

    private final String vr = "visitors_records:";

    private final String avr = "article_visitors_record:";

    private final String rd = "region_distributions:";

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
}
