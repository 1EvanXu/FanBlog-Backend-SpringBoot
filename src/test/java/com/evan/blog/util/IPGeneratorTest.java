package com.evan.blog.util;

import org.junit.Test;

public class IPGeneratorTest {
    @Test
    public void getRandomIP() {
        String ip = IPGenerator.getRandomIp();
        System.out.println(ip);
    }

}