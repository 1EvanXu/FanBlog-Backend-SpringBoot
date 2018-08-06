package com.evan.blog.service;

import com.evan.blog.pojo.IPLocation;


public interface IPQueryService {
    IPLocation query(String ip);
}
