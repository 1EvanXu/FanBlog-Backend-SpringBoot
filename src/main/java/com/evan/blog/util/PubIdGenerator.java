package com.evan.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
/*
    还需要改进的地方：服务器停机重启之后，numberOfPub的值会丢失，等redis搭建好了之后可以将numberOfPub放入redis
    改为一个Component
 */
@Component(value = "pubIdGenerator")
public class PubIdGenerator {

    @Autowired
    RedisOperator redisOperator;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    private static final int MAX_NUMBER_OF_PUB_EACH_DAY = 1000;

    private static volatile int oldPubIdPrefix;

    private static AtomicInteger numberOfPub = new AtomicInteger(0);

    private final String key = "pub_no:";

    public static int generatePubId() {
        int pubIdPrefix = Integer.parseInt(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        int pubId = pubIdPrefix * MAX_NUMBER_OF_PUB_EACH_DAY;

        if (pubIdPrefix != oldPubIdPrefix) {
            oldPubIdPrefix = pubIdPrefix;
            numberOfPub.set(0);
        }

        pubId += numberOfPub.getAndIncrement();
        return pubId;
    }

    public int generatePubId1() {
        int pubIdPrefix = Integer.parseInt(LocalDateTime.now().format(DATE_TIME_FORMATTER));
        int pubId = pubIdPrefix * MAX_NUMBER_OF_PUB_EACH_DAY;
        try {
            if (pubIdPrefix != oldPubIdPrefix) {
                oldPubIdPrefix = pubIdPrefix;
                numberOfPub.set(0);
                redisOperator.set(key, "0");
            }
            numberOfPub.set((int) redisOperator.incr(key, 1L));
            pubId += numberOfPub.get();
        } catch (RedisSystemException e) {
            pubId += numberOfPub.getAndIncrement();
        }
        return pubId;
    }
}

