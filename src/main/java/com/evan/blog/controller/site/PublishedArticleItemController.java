package com.evan.blog.controller.site;

import com.evan.blog.model.PublishedArticle;
import com.evan.blog.model.PublishedArticleItem;
import com.evan.blog.service.interfaces.PublishedArticleService;
import com.evan.blog.util.BlogJSONResult;
import com.evan.blog.util.ItemListData;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/articles")
public class PublishedArticleItemController {

    @Autowired
    PublishedArticleService publishedArticleService;



    @GetMapping(path = "/all/p/{pageIndex}")
    public BlogJSONResult getAllPublishedArticleItems(
            @PathVariable int pageIndex
    ) {
        PageInfo<PublishedArticle> publishedArticlePageInfo = publishedArticleService.getAllPublishedArticles(pageIndex);
        List<PublishedArticle> articles = publishedArticlePageInfo.getList();
        List<PublishedArticleItem> publishedArticleItems = new ArrayList<>();
        for (PublishedArticle p: articles) {
            publishedArticleItems.add(p.toInitialItem());
        }
        return BlogJSONResult.ok(new ItemListData((int)publishedArticlePageInfo.getTotal(), publishedArticleItems));

    }

    @GetMapping(path = "/category/{categoryId}/p/{pageIndex}")
    public BlogJSONResult getPublishedArticleItemsByCategory(
            @PathVariable("categoryId") Integer category,
            @PathVariable("pageIndex") Integer pageIndex
    ) {
        PageInfo<PublishedArticle> publishedArticlePageInfo =
                publishedArticleService.getPublishedArticlesByCategoryId(category, pageIndex);
        List<PublishedArticle> articles = publishedArticlePageInfo.getList();
        List<PublishedArticleItem> publishedArticleItems = new ArrayList<>();
        for (PublishedArticle p: articles) {
            publishedArticleItems.add(p.toInitialItem());
        }
        return BlogJSONResult.ok(new ItemListData((int)publishedArticlePageInfo.getTotal(), publishedArticleItems));
    }
}
