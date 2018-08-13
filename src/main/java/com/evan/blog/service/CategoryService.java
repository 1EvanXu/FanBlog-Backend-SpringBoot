package com.evan.blog.service;

import com.evan.blog.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    List<Category> findCategoriesByName(String name);
    void addCategoryIfAbsent(Category category);
    boolean removeCategories(List<Integer> categoryIds);
}
