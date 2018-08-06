package com.evan.blog.service;

import com.evan.blog.pojo.VisitorRecord;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.Map;
import java.util.Set;

public interface VisitorRecordCacheService {
    void recordVisitor(VisitorRecord record);
    Long getVisitorRecordCount();
    void updateRegionDistributions(String city);
    Set<ZSetOperations.TypedTuple<String>> getRegionDistributions();
    void addVisitorsRecord(Integer pubId, VisitorRecord record);
    Long getVisitorsCount(Integer pubId);
}
