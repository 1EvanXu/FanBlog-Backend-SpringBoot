package com.evan.blog.controller.management;

import com.evan.blog.model.Draft;
import com.evan.blog.model.Category;
import com.evan.blog.model.TempArticle;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.TempDraft;
import com.evan.blog.service.DraftCacheService;
import com.evan.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/editor")
public class EditorController {

    @Autowired
    DraftCacheService draftCacheService;
    @Autowired
    ArticleService articleService;


    @GetMapping(path = "/article/new")
    public BlogJSONResult newArticle() {
        return BlogJSONResult.ok(draftCacheService.generateTempArticleId());
    }

    @GetMapping(path = "/article/{articleId}")
    public BlogJSONResult getArticleContent(@PathVariable("articleId") Long id) {
        TempDraft articleContent = draftCacheService.getDraftContent(id);
        return BlogJSONResult.ok(articleContent);
    }

    @PutMapping(path = "/cache")
    public BlogJSONResult saveDraftInCache(@RequestBody TempDraft tempDraft) throws IllegalAccessException {
        long l = draftCacheService.saveDraftInCache(tempDraft);
//        System.out.println(tempDraft);
        BlogJSONResult result = BlogJSONResult.ok(l);
        result.setMsg("SAVED");
        return result;
    }

    @PostMapping(path = "/article")
    public BlogJSONResult saveArticle(@RequestBody Draft draft) throws IllegalAccessException {
        Long articleId = draftCacheService.saveDraft(draft);
//        System.out.println(draft);
        return BlogJSONResult.ok(articleId);
    }

    @PostMapping(path = "/publish")
    public BlogJSONResult publishArticle(@RequestBody TempArticle tempArticle) {
        System.out.println(tempArticle.getCategory());
        articleService.addArticle(tempArticle);
        BlogJSONResult result = BlogJSONResult.ok();
        result.setMsg("Publish succeed!");
        return result;
    }

    @GetMapping(path = "/category")
    public BlogJSONResult searchCategory(@RequestParam(value = "keyword") String keyword) {
        List<Category> categories = draftCacheService.searchCategoryByName(keyword);
        return BlogJSONResult.ok(categories);
    }

}
