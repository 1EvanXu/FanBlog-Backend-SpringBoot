package com.evan.blog.controller;

import com.evan.blog.model.Article;
import com.evan.blog.model.ArticleQueryFilter;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.TempArticle;
import com.evan.blog.model.enums.ArticleType;
import com.evan.blog.model.enums.Order;
import com.evan.blog.pojo.ArticleDetails;
import com.evan.blog.pojo.ArticleItem;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemCollection;
import com.evan.blog.pojo.management.ArticlesManagementListItem;
import com.evan.blog.service.ArticleCacheService;
import com.evan.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/articles")
public class ArticlesController {

    @Autowired
    @Qualifier(value = "articleService")
    ArticleService articleService;

    @Autowired
    @Qualifier(value = "articleCacheService")
    ArticleCacheService articleCacheService;

    @GetMapping(path = "/{pubId}")
    public BlogJSONResult getArticleDetails(@PathVariable Long pubId) {
        ArticleDetails articleDetails = articleService.getArticle(pubId);
        return BlogJSONResult.ok(articleDetails);
    }

    @PostMapping(path = "/")
    public BlogJSONResult postArticle(@RequestBody TempArticle tempArticle) {
        articleService.addArticle(tempArticle);
//        System.out.println(tempArticle.getCategory());
        BlogJSONResult result = BlogJSONResult.ok();
        result.setMsg("Publish succeed!");
        return result;
    }


    @GetMapping(path = "items/p/{pageIndex}")
    public BlogJSONResult getAllArticlesItems(
            @PathVariable int pageIndex,
            @RequestParam(value = "categoryId", required = false) Long categoryId) {
        PageInfo<ArticleItem> publishedArticlePageInfo = null;

        if (categoryId != null) {
            publishedArticlePageInfo = articleService.getArticleItemsByCategoryId(categoryId, pageIndex);
        } else {
            publishedArticlePageInfo = articleService.getAllArticleItems(pageIndex);
        }
        List<ArticleItem> articleItems = publishedArticlePageInfo.getList();

        return BlogJSONResult.ok(new ItemCollection((int)publishedArticlePageInfo.getTotal(), articleItems));

    }


    @GetMapping(path = "managementItems/p/{pageIndex}")
    public BlogJSONResult getArticleManagementItems(@PathVariable int pageIndex,
                                                    @RequestParam("orderField") String orderField,
                                                    @RequestParam("order") String order,
                                                    @RequestParam("type") String type
    ) {
        QueryFilter queryFilter = new ArticleQueryFilter(orderField, Order.getOrder(order), ArticleType.getType(type));
        List<ArticlesManagementListItem> items = new ArrayList<>();

        PageInfo<Article> ArticlePageInfo = articleService.getArticlesByFilter(pageIndex, queryFilter);


        ArticlePageInfo.getList().forEach(item ->
                items.add(new ArticlesManagementListItem(item))
        );

        return BlogJSONResult.ok(new ItemCollection((int)ArticlePageInfo.getTotal(), items));
    }

    @DeleteMapping(path = "/")
    public BlogJSONResult deleteArticles(@RequestParam("ids") String ids) throws Exception {
        List<Long> pubIds = transferIdParams(ids);
        // remove related info from DB.
        articleService.deleteArticles(pubIds);
        // the remove related info from Redis.
        pubIds.forEach(pubId -> {
            articleCacheService.removePublishedArticleFromCache(pubId.toString());
        });
        return BlogJSONResult.ok();
    }

    private List<Long> transferIdParams(String stringIds) {
        String[] strings = stringIds.split(",");
        List<Long> ids = new ArrayList<>(strings.length);
        for (String s: strings) {
            ids.add(Long.valueOf(s));
        }
        return ids;
    }
}
