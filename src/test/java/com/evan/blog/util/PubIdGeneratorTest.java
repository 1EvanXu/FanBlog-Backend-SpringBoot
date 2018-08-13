package com.evan.blog.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PubIdGeneratorTest {

    @Autowired
    PubIdGenerator pubIdGenerator;

    @Test
    public void generatePubId() {
        System.out.println("pubId: " + pubIdGenerator.generatePubId());
    }

    //线程安全测试
    @Test
    public void threadSafeGeneratePubId() {
        int initialCapacity = 1000;
        List<Thread> threads = new ArrayList<>(initialCapacity);
        ConcurrentHashMap<Integer, String> resultMap = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    resultMap.put(pubIdGenerator.generatePubId(), Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            }));
        }

        for (Thread thread : threads) {
            thread.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(1000, resultMap.size());

    }
}