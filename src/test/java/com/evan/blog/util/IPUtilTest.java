package com.evan.blog.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RunWith(SpringRunner.class)
@SpringBootTest
public class IPUtilTest {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void IPv4ToLong() {
        long iPv4ToLong = IPUtil.IPv4ToLong(IPUtil.getRandomIp());
        long l = System.currentTimeMillis();
        System.out.println((l / 300000) * 300000);
    }

    @Test()
    public void IPv4ToString() {
        System.out.println(IPUtil.IPv4ToString(1779350079L));
    }

    @Test
    public void transfer() {

        String url = "/blog/article/180805001/";
        Pattern pattern = Pattern.compile(".*/blog/article/(\\d+)/?$");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }
        AtomicLong atomicLong = new AtomicLong(0);
        System.out.println(atomicLong.getAndSet(1));
    }

    @Test
    public void redisTemplateTest() {
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) redisTemplate.getConnectionFactory();
        connectionFactory.setDatabase(1);
    }

    @Test
    public void getRandomIp () {
        System.out.println(IPUtil.getRandomIp());
    }
}