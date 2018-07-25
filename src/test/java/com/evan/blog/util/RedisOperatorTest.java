package com.evan.blog.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
}