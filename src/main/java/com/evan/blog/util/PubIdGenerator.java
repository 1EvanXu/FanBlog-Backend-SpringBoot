package com.evan.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component(value = "pubIdGenerator")
public class PubIdGenerator {

    @Autowired
    RedisOperator redisOperator;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    private static final int MAX_NUMBER_OF_PUB_EACH_DAY = 1000;

    private static int oldPubIdPrefix;

    private final static Lock lock = new ReentrantLock();

    private final String key = "pub_number:";

    public long generatePubId() {
        int pubIdPrefix = Integer.parseInt(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        int pubId = pubIdPrefix * MAX_NUMBER_OF_PUB_EACH_DAY;

        lock.lock();
        try {
            if (pubIdPrefix != oldPubIdPrefix) {
                oldPubIdPrefix = pubIdPrefix;
                redisOperator.set(key, "0");
            }
            pubId += redisOperator.incr(key, 1L);

        } finally {
            lock.unlock();
        }
        return pubId;

    }
}

