package com.evan.blog.controller;

import com.evan.blog.model.Draft;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.TempDraft;
import com.evan.blog.service.DraftCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/cache/drafts")
public class DraftCacheController {

    @Autowired
    DraftCacheService draftCacheService;

    @GetMapping(path = "")
    public BlogJSONResult newArticle() {
        return BlogJSONResult.ok(draftCacheService.generateTempArticleId());
    }

    @GetMapping(path = "/{draftId}")
    public BlogJSONResult getArticleContent(@PathVariable long draftId) {
        TempDraft articleContent = draftCacheService.getDraftContent(draftId);
        return BlogJSONResult.ok(articleContent);
    }

    @PutMapping(path = "/")
    public BlogJSONResult saveDraftInCache(@RequestBody TempDraft tempDraft) throws IllegalAccessException {
        long l = draftCacheService.saveDraftInCache(tempDraft);

        BlogJSONResult result = BlogJSONResult.ok(l);
        result.setMsg("SAVED");
        return result;
    }

    @PostMapping(path = "")
    public BlogJSONResult postDraft(@RequestBody Draft draft) throws IllegalAccessException {
        Long articleId = draftCacheService.saveDraft(draft);

        return BlogJSONResult.ok(articleId);
    }
}
