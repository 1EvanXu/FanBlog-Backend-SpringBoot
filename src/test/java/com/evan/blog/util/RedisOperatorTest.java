package com.evan.blog.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisOperatorTest {

    @Autowired
    RedisOperator redisOperator;

    @Test
    public void set() {
        redisOperator.set("hello", "world");
    }
    @Test
    public void get() {
        assertEquals("world", redisOperator.get("hello"));
    }
    @Test
    public void del() {
        redisOperator.del("hello");
    }
    @Test
    public void zrevrangebyscore() {
        Set<ZSetOperations.TypedTuple<String>> set =
                redisOperator.zrevrangebyscore("ip2cityid:", 0, 1779350079, 0, 1);
        assertEquals(1, set.size());
    }

    @Test
    public void zcard() {
        long l = redisOperator.zcard("voted:180721499");
        System.out.println(l);
    }

    @Test
    public void zrank() {
        Long rank = redisOperator.zrank("voted:180721499", "192.168.1.101");
        assertNull(rank);
    }

    @Test
    public void pipezcard () {
        String votedPrefix = "voted:";
        Integer[] pubIds = new Integer[]{180721499, 180721490, 180721484, 180721478, 180721471, 180721454};
        String[] keys = new String[pubIds.length];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = votedPrefix + pubIds[i];
            System.out.println(keys[i]);
        }
        Long[] pipezcard = redisOperator.pipezcard(keys);
        assertEquals(keys.length, pipezcard.length);
    }

    @Test
    public void set1() {
        String key = "test:expire";
        redisOperator.set(key, "hello world!", 20);
        long ttl = redisOperator.ttl(key);
        boolean isExpired = ttl == 0 || ttl == -2;
        System.out.println("ttl: "+ ttl + " expired:" + isExpired);
        redisOperator.set(key, "Hello World!");
        ttl = redisOperator.ttl(key);
        isExpired = ttl == 0 || ttl == -2;
        System.out.println("ttl: "+ ttl + " expired:" + isExpired);
    }
}