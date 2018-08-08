package com.evan.blog.service.impls;

import com.evan.blog.service.SiteInfoCacheService;
import com.evan.blog.util.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;

public class SiteInfoCacheServiceImpl implements SiteInfoCacheService {

    @Autowired
    RedisOperator redisOperator;

    @Override
    public void pageViewCount() {

    }

    @Override
    public Integer getPageViewCount() {
        return null;
    }
}
