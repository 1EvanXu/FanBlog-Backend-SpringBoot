package com.evan.blog.controller.site;

import com.evan.blog.pojo.PublishedArticleItem;
import com.evan.blog.service.PublishedArticleService;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemCollection;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        PageInfo<PublishedArticleItem> publishedArticlePageInfo = publishedArticleService.getAllPublishedArticleItems(pageIndex);
        List<PublishedArticleItem> publishedArticleItems = publishedArticlePageInfo.getList();

        return BlogJSONResult.ok(new ItemCollection((int)publishedArticlePageInfo.getTotal(), publishedArticleItems));

    }

    @GetMapping(path = "/category/{categoryId}/p/{pageIndex}")
    public BlogJSONResult getPublishedArticleItemsByCategory(
            @PathVariable("categoryId") Integer category,
            @PathVariable("pageIndex") Integer pageIndex
    ) {
        PageInfo<PublishedArticleItem> publishedArticlePageInfo =
                publishedArticleService.getPublishedArticleItemsByCategoryId(category, pageIndex);
        List<PublishedArticleItem> publishedArticleItems = publishedArticlePageInfo.getList();

        return BlogJSONResult.ok(new ItemCollection((int)publishedArticlePageInfo.getTotal(), publishedArticleItems));
    }

    /*
    note:
    reform the url to real RESTful
    /articles/p/{pageIndex} -> default all
    /articles/p/{pageIndex}?category={categoryId} -> spec categoryId
    /articles/p/{pageIndex}?keywords={keywords} -> search by keywords
    /articles/
     */
}
