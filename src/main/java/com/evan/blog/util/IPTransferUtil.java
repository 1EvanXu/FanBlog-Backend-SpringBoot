package com.evan.blog.util;

public class IPTransferUtil {

    private final static long PART1 = 0xff000000;
    private final static long PART2 = 0xff0000;
    private final static long PART3 = 0xff00;
    private final static long PART4 = 0xff;

    /** 将IP地址长整型数值转化为IPv4字符串 */
    public static String IPv4ToString(long ip) {
        String ipStr = String.valueOf((ip & PART1) >> 24);
        ipStr += "." + ((ip & PART2) >> 16);
        ipStr += "." + ((ip & PART3) >> 8);
        ipStr += "." + (ip & PART4);
        return ipStr;
    }

    /** 将IPv4字符串转化为对应的长整型整数 */
    public static long IPv4ToLong(String ip) {
        String[] p4 = ip.split("\\.");
        long ipInt = 0;
        long part = Long.valueOf(p4[0]);
        ipInt = ipInt | (part << 24);
        part = Long.valueOf(p4[1]);
        ipInt = ipInt | (part << 16);
        part = Long.valueOf(p4[2]);
        ipInt = ipInt | (part << 8);
        part = Long.valueOf(p4[3]);
        ipInt = ipInt | (part);
        return ipInt;
    }

}


