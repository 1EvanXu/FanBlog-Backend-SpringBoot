package com.evan.blog.controller;

import com.evan.blog.interceptor.AccessLevel;
import com.evan.blog.model.enums.UserLevel;
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
    public BlogJSONResult newDraft() {
        return BlogJSONResult.ok(draftCacheService.generateTempArticleId());
    }

    @GetMapping(path = "/{draftId}")
    public BlogJSONResult getDraftContent(@PathVariable long draftId) {
        TempDraft draftContent = draftCacheService.getDraftContent(draftId);
        draftContent.setHtmlContent(null);
        return BlogJSONResult.ok(draftContent);
    }

    @PutMapping(path = "")
    @AccessLevel(roles = {UserLevel.Admin, UserLevel.VIP})
    public BlogJSONResult saveDraftInCache(@RequestBody TempDraft tempDraft) throws IllegalAccessException {
        long l = draftCacheService.saveDraftInCache(tempDraft);

        BlogJSONResult result = BlogJSONResult.ok(l);
        result.setMsg("SAVED");
        return result;
    }

    @PostMapping(path = "")
    @AccessLevel(roles = {UserLevel.Admin, UserLevel.VIP})
    public BlogJSONResult postDraft(@RequestBody TempDraft tempDraft) {
        Long draftId = draftCacheService.saveDraft(tempDraft);
        return BlogJSONResult.ok(draftId);
    }
}
