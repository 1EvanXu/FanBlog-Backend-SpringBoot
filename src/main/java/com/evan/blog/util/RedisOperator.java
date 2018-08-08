package com.evan.blog.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 *
 * @Title: RedisOperator.java
 * @Package com.evan.blog.util
 * @Description: 使用redisTemplate的操作实现类 Copyright: Copyright (c) 2016
 *               Company:FURUIBOKE.SCIENCE.AND.TECHNOLOGY
 *
 * @author Evan Xu
 * @date 2018年7月28日
 * @version V1.0
 */
@Component
public class RedisOperator {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // Key（键），简单的key-value操作

    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public long ttl(String key) {
        return redisTemplate.getExpire(key);
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key key
     * @param timeout 过期时间
     * @return
     */
    public void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：INCR key，增加key一次
     *
     * @param key key
     * @param delta 增量
     * @return
     */
    public long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public void del(String key) {
        redisTemplate.delete(key);
    }

    // String（字符串）

    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout
     *            （以秒为单位）
     */
    public void set(String key, String value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    // Hash（哈希表）

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        return (String) redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields) {
        redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hgetall(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    // List（列表）

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long lpush(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public String lpop(String key) {
        return (String)redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public long rpush(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 实现命令：RPOP key，移除并返回列表 key的尾元素。
     *
     * @param key
     * @return 列表key的尾元素。
     */
    public String rpop(String key) {
        return (String)redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 实现命令：LRANGE key start stop，返回列表key中指定区间内的元素，区间以偏移量start和stop指定。
     *
     * @param key 列表的key值
     * @Param start 列表区间的起始值
     * @Param end 列表区间的终止值
     * @return 执行 LRANGE命令后，返回列表所有元素。
     */
    public List<String> lrange (String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }


    /** 当列表大小大于给定容量时，弹出元素直至列表中元素个数在给定容量之下
     * @param key 列表的key值
     * @param value 存入列表key的value值
     */
    public void lpushrpop(String key, String value, Long listCapacity) {
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(key, value);

//        redisTemplate.watch(key);
//        redisTemplate.multi();
        Long remainCapacity = listOperations.size(key) - listCapacity;
        if (remainCapacity > 0) {
            for (int i = 0; i < remainCapacity.intValue(); i++) {
                listOperations.rightPop(key);
            }
        }
//        redisTemplate.exec();
    }

    // Zset 有序集合
    public boolean zadd(String key, String field, Double score) {
        return redisTemplate.opsForZSet().add(key, field, score);
    }

    public Set<ZSetOperations.TypedTuple<String>> zrevrange (String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end);
    }

    public Set<ZSetOperations.TypedTuple<String>> zrevrangebyscore (String key, double min, double max, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, min, max, offset, count);
    }

    public Long zrank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key, value);
    }

    public long zcard (String key) {
        return redisTemplate.opsForZSet().zCard(key);
    }

    public Long[] pipezcard (String[] keys) {

        RedisCallback<List<Object>> redisCallback = new RedisCallback<List<Object>>() {
            @Override
            public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
                connection.openPipeline();
                for (String key: keys) {
                    connection.zCard(key.getBytes());
                }
                return connection.closePipeline();
            }
        };

        List<Object> execResults = redisTemplate.execute(redisCallback);
        Long[] results = new Long[execResults.size()];
        for (int i = 0; i < results.length; i++) {
            results[i] = (Long) execResults.get(i);
        }

        return results;
    }

    public Object pipline(RedisCallback<Object> redisCallback) {
        return redisTemplate.execute(redisCallback);
    }

    public void zincr(String key, String value, double score) {
        redisTemplate.opsForZSet().incrementScore(key, value, score);
    }
}

