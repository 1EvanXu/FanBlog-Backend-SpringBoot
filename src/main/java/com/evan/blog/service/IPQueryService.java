package com.evan.blog.service;

public interface IPQueryService {
    String[] query(String ip);
    int ipToScore(String ip, boolean isIPv6);
    boolean IPv4FormatCheck(String ipv4);
    boolean IPv6FormatCheck(String ipv6);
}
