package com.evan.blog.service;

import com.evan.blog.model.Category;
import com.evan.blog.model.QueryFilter;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CategoryService {
    PageInfo<Category> getCategories(Integer pageIndex, QueryFilter queryFilter);
    List<Category> findCategoriesByName(String name);
    void addCategoryIfAbsent(Category category);
    boolean removeCategories(List<Integer> categoryIds);
}
