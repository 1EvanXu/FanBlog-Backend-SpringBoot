package com.evan.blog.service;

import com.evan.blog.pojo.SideInfoItem;

import java.util.List;

public interface SideInfoService {
    List<SideInfoItem> getLatestPublishedArticle();
    List<SideInfoItem> getCategoriesInfo();
    List<SideInfoItem> getPopularPublishedArticle();
}
