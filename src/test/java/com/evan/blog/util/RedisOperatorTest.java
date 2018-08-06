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
}