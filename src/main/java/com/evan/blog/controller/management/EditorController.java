package com.evan.blog.controller.management;

import com.evan.blog.model.Article;
import com.evan.blog.model.Category;
import com.evan.blog.model.PublishingArticle;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.Draft;
import com.evan.blog.service.EditorService;
import com.evan.blog.service.PublishedArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/editor")
public class EditorController {

    @Autowired
    EditorService editorService;
    @Autowired
    PublishedArticleService publishedArticleService;


    @GetMapping(path = "/article/new")
    public BlogJSONResult newArticle() {
        return BlogJSONResult.ok(editorService.generateTempArticleId());
    }

    @GetMapping(path = "/article/{articleId}")
    public BlogJSONResult getArticleContent(@PathVariable Integer articleId) {
        Draft articleContent = editorService.getArticleContent(articleId);
        return BlogJSONResult.ok(articleContent);
    }

    @PutMapping(path = "/cache")
    public BlogJSONResult saveDraftInCache(@RequestBody Draft draft) throws IllegalAccessException {
        long l = editorService.saveDraftInCache(draft);
//        System.out.println(draft);
        BlogJSONResult result = BlogJSONResult.ok(l);
        result.setMsg("SAVED");
        return result;
    }

    @PostMapping(path = "/article")
    public BlogJSONResult saveArticle(@RequestBody Article article) throws IllegalAccessException {
        Integer articleId = editorService.saveArticle(article);
//        System.out.println(article);
        return BlogJSONResult.ok(articleId);
    }

    @PostMapping(path = "/publish")
    public BlogJSONResult publishArticle(@RequestBody PublishingArticle publishingArticle) {
        System.out.println(publishingArticle.getCategory());
        publishedArticleService.addPublishedArticle(publishingArticle);
        BlogJSONResult result = BlogJSONResult.ok();
        result.setMsg("Publish succeed!");
        return result;
    }

    @GetMapping(path = "/category")
    public BlogJSONResult searchCategory(@RequestParam(value = "keyword") String keyword) {
        List<Category> categories = editorService.searchCategoryByName(keyword);
        return BlogJSONResult.ok(categories);
    }

}
