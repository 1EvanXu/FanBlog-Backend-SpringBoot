package com.evan.blog.controller.site;

import com.evan.blog.pojo.ItemListData;
import com.evan.blog.pojo.SideInfoItem;
import com.evan.blog.service.SideInfoService;
import com.evan.blog.pojo.BlogJSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/side-info")
public class SideInfoController {

    @Autowired
    @Qualifier(value = "sideInfoService")
    SideInfoService sideInfoService;

    @GetMapping(path = "/latest-articles")
    public BlogJSONResult getLatestArticles() {
        List<SideInfoItem> latestPublishedArticles = sideInfoService.getLatestPublishedArticle();
        ItemListData data = new ItemListData(latestPublishedArticles.size(), latestPublishedArticles);
        return BlogJSONResult.ok(data);
    }

    @GetMapping(path = "/popular-articles")
    public BlogJSONResult getPopularArticles() {
        List<SideInfoItem> popularPublishedArticles = sideInfoService.getPopularPublishedArticle();
        ItemListData data = new ItemListData(popularPublishedArticles.size(), popularPublishedArticles);
        return BlogJSONResult.ok(data);
    }

    @GetMapping(path = "/categories")
    public BlogJSONResult getCategories() {
        List<SideInfoItem> categoriesInfo = sideInfoService.getCategoriesInfo();
        ItemListData data = new ItemListData(categoriesInfo.size(), categoriesInfo);
        return BlogJSONResult.ok(data);
    }

}
