package com.evan.blog.util;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPUtilTest {

    @Test
    public void IPv4ToLong() {
        long iPv4ToLong = IPUtil.IPv4ToLong(IPUtil.getRandomIp());
        System.out.println(iPv4ToLong + "\u5c71\u897f");
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
    public void getRandomIp () {
        System.out.println(IPUtil.getRandomIp());
    }
}