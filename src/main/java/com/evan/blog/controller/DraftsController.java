package com.evan.blog.controller;

import com.evan.blog.model.Draft;
import com.evan.blog.model.DraftQueryFilter;
import com.evan.blog.model.enums.DraftStatus;
import com.evan.blog.model.enums.Order;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemCollection;
import com.evan.blog.pojo.management.DraftStatusUpdate;
import com.evan.blog.pojo.management.DraftsManagementListItem;
import com.evan.blog.service.DraftService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "apis/drafts")
public class DraftsController {

    @Autowired
    DraftService draftService;

    @GetMapping(path = "/managementItems/p/{pageIndex}")
    public BlogJSONResult getDraftsManagementList(
            @PathVariable("pageIndex") Integer pageIndex,
            @RequestParam("orderField") String orderField,
            @RequestParam("order") String order
    ) {
        DraftQueryFilter queryFilter = new DraftQueryFilter(orderField, Order.getOrder(order), DraftStatus.Editing);

        List<DraftsManagementListItem> items = new ArrayList<>();

        PageInfo<Draft> articlePageInfo = draftService.getDrafts(pageIndex, queryFilter);

        articlePageInfo.getList().forEach(item -> items.add(new DraftsManagementListItem(item)));

        return BlogJSONResult.ok(new ItemCollection((int) articlePageInfo.getTotal(), items));
    }

    @PutMapping(path = "/status")
    public BlogJSONResult updateDraftStatus(@RequestBody DraftStatusUpdate update) {
        System.out.println(update);
        draftService.updateDraftStatus(update.getStatus(), update.getArticleIds());
        return BlogJSONResult.ok();
    }

    @DeleteMapping(value = "")
    public BlogJSONResult deleteDrafts(@RequestParam("ids") String ids) {
        List<Long> articleIds = transferIdParams(ids);
        System.out.println(articleIds);
        draftService.removeDrafts(articleIds);
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
