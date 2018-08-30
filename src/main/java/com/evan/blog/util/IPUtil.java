package com.evan.blog.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;

public class IPUtil {

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

    public static String getRandomIp(){
        //ip范围
        int[][] range = {{607649792,608174079},//36.56.0.0-36.63.255.255
                {1038614528,1039007743},//61.232.0.0-61.237.255.255
                {1783627776,1784676351},//106.80.0.0-106.95.255.255
                {2035023872,2035154943},//121.76.0.0-121.77.255.255
                {2078801920,2079064063},//123.232.0.0-123.235.255.255
                {-1950089216,-1948778497},//139.196.0.0-139.215.255.255
                {-1425539072,-1425014785},//171.8.0.0-171.15.255.255
                {-1236271104,-1235419137},//182.80.0.0-182.92.255.255
                {-770113536,-768606209},//210.25.0.0-210.47.255.255
                {-569376768,-564133889}, //222.16.0.0-222.95.255.255
        };

        Random randomInt = new Random();
        int index = randomInt.nextInt(10);
        Integer integer = range[index][0]+new Random().nextInt(range[index][1]-range[index][0]);
        String ip = num2ip(range[index][0] + new Random().nextInt(range[index][1] - range[index][0]));
        return ip;
    }

    public static String num2ip(int ip) {
        int[] b = new int[4];
        String x = "";

        b[0] = (int) ((ip >> 24) & 0xff);
        b[1] = (int) ((ip >> 16) & 0xff);
        b[2] = (int) ((ip >> 8) & 0xff);
        b[3] = (int) (ip & 0xff);
        x = Integer.toString(b[0]) + "." + Integer.toString(b[1]) + "." + Integer.toString(b[2]) + "." + Integer.toString(b[3]);

        return x;
    }

    public static String getRealIP(HttpServletRequest request) {

        String ip = request.getHeader("X-Forwarded-For");

        if(!(ip == null || ip.equals("")) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(!(ip == null || ip.equals("")) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        ip = request.getRemoteAddr();
        if (ip.startsWith("192.168.") || ip.startsWith("www.") || ip == "localhost" || ip == "127.0.0.1") {
            ip = getRandomIp();
        }
        return ip;
    }

}


