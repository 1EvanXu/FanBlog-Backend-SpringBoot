package com.evan.blog.service;

import com.evan.blog.pojo.VisitorRecord;

import java.util.Map;

public interface VisitorRecordCacheService {
    void recordVisitor(VisitorRecord record);
    Integer getVisitorRecordCount();
    void updateRegionDistributions(String city);
    Map<String, Integer> getRegionDistributions();
    void addVisitorsRecord(Integer pubId, VisitorRecord record);
    Integer getVisitorsCount(Integer pubId);
}
