package com.evan.blog.controller.management;

import com.evan.blog.model.Article;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.Draft;
import com.evan.blog.service.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/editor")
public class EditorController {

    @Autowired
    EditorService editorService;

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
    public BlogJSONResult saveDraftInCache(Draft draft) {
        long l = editorService.saveDraftInCache(draft);
        return BlogJSONResult.ok(l);
    }

    @PostMapping(path = "/article")
    public BlogJSONResult saveArticle(Article article) {
        long l = editorService.saveArticle(article);
        return BlogJSONResult.ok(l);
    }

    @PostMapping(path = "/publish")
    public BlogJSONResult publishArticle() {
        return BlogJSONResult.ok();
    }


}
