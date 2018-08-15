package com.evan.blog.controller.management;

import com.evan.blog.model.Category;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.model.enums.Order;
import com.evan.blog.pojo.BlogJSONResult;
import com.evan.blog.pojo.ItemListData;
import com.evan.blog.pojo.management.CategoriesManagementListItem;
import com.evan.blog.service.CategoryService;
import com.evan.blog.util.JsonUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/management")
public class CategoriesManagementController {
    @Autowired
    CategoryService categoryService;
    @GetMapping(value = "/categories/p/{pageIndex}")
    public BlogJSONResult getCategoriesManagementList(
            @PathVariable("pageIndex") Integer pageIndex,
            @RequestParam("orderField") String orderField,
            @RequestParam("order") String order) {
        QueryFilter queryFilter = new QueryFilter(orderField, Order.getOrder(order));

        PageInfo<Category> categoryPageInfo = categoryService.getCategories(pageIndex, queryFilter);
        List<CategoriesManagementListItem> items = new ArrayList<>();

        categoryPageInfo.getList().forEach(item -> items.add(new CategoriesManagementListItem(item)));

        return BlogJSONResult.ok(new ItemListData((int) categoryPageInfo.getTotal(), items));
    }
}
