package com.evan.blog.service.impls;

import com.evan.blog.pojo.VisitorRecord;
import com.evan.blog.service.SiteInfoCacheService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "siteInfoCacheService")
public class SiteInfoCacheServiceImpl implements SiteInfoCacheService {

    @Autowired
    RedisOperator redisOperator;

    String rdKey = "region_distributions:";

    @Override
    public void pageViewCount() {

    }

    @Override
    public Long getPageViewCount() {
        return 0L;
    }

    @Override
    public void updateRegionDistribution(VisitorRecord record) {
        redisOperator.zadd(rdKey, record.getIpLocation().getCity(), 1.0);
    }
}
