package com.evan.blog.service;

import com.evan.blog.pojo.VisitorRecord;

public interface SiteInfoCacheService {
    void pageViewCount();
    Long getPageViewCount();
    void updateRegionDistribution(VisitorRecord record);
}
