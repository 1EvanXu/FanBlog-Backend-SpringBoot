package com.evan.blog.repository;


import com.evan.blog.util.RedisOperator;
import org.apache.ibatis.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.locks.ReadWriteLock;

public class SecondLevelCache implements Cache {

    private final String id;

    SecondLevelCache(String id) {
        if (id == null || id.equals("")) {
            throw new IllegalArgumentException("Cache instance require an ID.");
        }
        this.id = id;
    }

    @Autowired
    RedisOperator redisOperator;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {

    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
