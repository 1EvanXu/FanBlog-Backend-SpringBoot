package com.evan.blog.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class PubIdGeneratorTest {

    @Test
    public void generatePubId() {
        System.out.println("pubId: " + PubIdGenerator.generatePubId());
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
                    resultMap.put(PubIdGenerator.generatePubId(), Thread.currentThread().getName());
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