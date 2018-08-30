package com.evan.blog.util;


import org.junit.Test;

public class IPUtilTest {

    @Test
    public void IPv4ToLong() {
        IPUtil.IPv4ToLong(IPUtil.getRandomIp());
    }

    @Test()
    public void IPv4ToString() {
        System.out.println(IPUtil.IPv4ToString(1779350079L));
    }

    @Test
    public void transfer() {

    }

    @Test
    public void getRandomIp () {
        System.out.println(IPUtil.getRandomIp());
    }
}