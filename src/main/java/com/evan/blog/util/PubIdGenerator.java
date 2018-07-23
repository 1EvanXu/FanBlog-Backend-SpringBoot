package com.evan.blog.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
/*
    还需要改进的地方：服务器停机重启之后，numberOfPub的值会丢失，等redis搭建好了之后可以将numberOfPub放入redis
 */

public class PubIdGenerator {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");

    private static final int MAX_NUMBER_OF_PUB_EACH_DAY = 1000;

    private static int oldPubIdPrefix = 0;

    private static AtomicInteger numberOfPub = new AtomicInteger(0);

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
}

