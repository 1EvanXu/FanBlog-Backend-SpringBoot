package com.evan.blog.controller;

import com.evan.blog.model.Category;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.Order;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemCollection;
import com.evan.blog.pojo.management.CategoriesManagementListItem;
import com.evan.blog.service.CategoryService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesController {
    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/p/{pageIndex}")
    public BlogJSONResult getCategories(
            @PathVariable("pageIndex") Integer pageIndex,
            @RequestParam("orderField") String orderField,
            @RequestParam("order") String order) {
        QueryFilter filter = new QueryFilter(orderField, Order.getOrder(order));

        List<CategoriesManagementListItem> items = new ArrayList<>();
        PageInfo<Category> categoryPageInfo = categoryService.getCategories(pageIndex, filter);

        categoryPageInfo.getList().forEach(item -> items.add(new CategoriesManagementListItem(item)));

        return BlogJSONResult.ok(new ItemCollection((int) categoryPageInfo.getTotal(), items));
    }

    @GetMapping(path = "")
    public BlogJSONResult searchCategory(@RequestParam(value = "keywords") String keywords) {
        List<Category> categories = categoryService.findCategoriesByName(keywords);
        return BlogJSONResult.ok(categories);
    }

    @PostMapping(value = "")
    public BlogJSONResult addCategory() {
        return BlogJSONResult.ok();
    }
}
