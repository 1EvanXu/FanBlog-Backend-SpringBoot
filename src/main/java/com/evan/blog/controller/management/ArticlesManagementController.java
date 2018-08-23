package com.evan.blog.controller.management;

import com.evan.blog.model.*;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.model.enums.Order;
import com.evan.blog.model.enums.ArticleType;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemCollection;
import com.evan.blog.pojo.management.ArticlesManagementListItem;
import com.evan.blog.pojo.management.ArticlesStatusUpdate;
import com.evan.blog.pojo.management.DeletedArticlesManagementListItem;
import com.evan.blog.pojo.management.DraftsManagementListItem;
import com.evan.blog.service.DraftService;
import com.evan.blog.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/management")
public class ArticlesManagementController {

    @Autowired
    ArticleService articleService;

    @Autowired
    DraftService draftService;

    @GetMapping(path = "/publishedArticles/p/{pageIndex}")
    public BlogJSONResult getPublishedArticlesManagementList(
            @PathVariable("pageIndex") Integer pageIndex,
            @RequestParam("orderField") String orderField,
            @RequestParam("order") String order,
            @RequestParam("type") String type
    ) {
        QueryFilter queryFilter = new ArticleQueryFilter(orderField, Order.getOrder(order), ArticleType.getType(type));

        PageInfo<Article> publishedArticlePageInfo = articleService.getArticlesByFilter(pageIndex, queryFilter);

        List<ArticlesManagementListItem> items = new ArrayList<>();

        publishedArticlePageInfo.getList().forEach(item ->
            items.add(new ArticlesManagementListItem(item))
        );

        return BlogJSONResult.ok(new ItemCollection((int)publishedArticlePageInfo.getTotal(), items));
    }

    @GetMapping(path = "/drafts/p/{pageIndex}")
    public BlogJSONResult getDraftsManagementList(
            @PathVariable("pageIndex") Integer pageIndex,
            @RequestParam("orderField") String orderField,
            @RequestParam("order") String order
    ) {
        DraftQueryFilter queryFilter = new DraftQueryFilter(orderField, Order.getOrder(order), DraftStatus.Editing);

        PageInfo<Draft> articlePageInfo = draftService.getDrafts(pageIndex, queryFilter);

        List<DraftsManagementListItem> items = new ArrayList<>();

        articlePageInfo.getList().forEach(item -> items.add(new DraftsManagementListItem(item)));

        return BlogJSONResult.ok(new ItemCollection((int) articlePageInfo.getTotal(), items));
    }

    @GetMapping(path = "/deletedArticles/p/{pageIndex}")
    public BlogJSONResult getDeletedArticlesManagementListItem(
            @PathVariable("pageIndex") Integer pageIndex,
            @RequestParam("orderField") String orderField,
            @RequestParam("order") String order
    ) {
        DraftQueryFilter queryFilter = new DraftQueryFilter(orderField, Order.getOrder(order), DraftStatus.Deleted);

        PageInfo<Draft> articlePageInfo = draftService.getDrafts(pageIndex, queryFilter);

        List<DeletedArticlesManagementListItem> items = new ArrayList<>();

        articlePageInfo.getList().forEach(item -> items.add(new DeletedArticlesManagementListItem(item)));

        return BlogJSONResult.ok(new ItemCollection((int) articlePageInfo.getTotal(), items));
    }

    @PutMapping(value = "/articles/status")
    public BlogJSONResult updateArticlesStatus(@RequestBody ArticlesStatusUpdate update) {
        System.out.println(update);
        draftService.updateDraftStatus(update.getStatus(), update.getArticleIds());
        return BlogJSONResult.ok();
    }

    @DeleteMapping(value = "/articles")
    public BlogJSONResult deleteArticlesPermanently(@RequestParam("ids") String ids) {
        List<Integer> articleIds = transferIdParams(ids);
        System.out.println(articleIds);
        draftService.removeDrafts(articleIds);
        return BlogJSONResult.ok();
    }

    @DeleteMapping(value = "/publishedArticles")
    public BlogJSONResult deletePublishedArticles(@RequestParam("ids") String ids) throws Exception {
        List<Integer> pubIds = transferIdParams(ids);
        System.out.println(pubIds);
        articleService.deleteArticles(pubIds);
        return BlogJSONResult.ok();
    }

    private List<Integer> transferIdParams(String stringIds) {
        String[] strings = stringIds.split(",");
        List<Integer> ids = new ArrayList<>(strings.length);
        for (String s: strings) {
            ids.add(Integer.valueOf(s));
        }
        return ids;
    }
}
