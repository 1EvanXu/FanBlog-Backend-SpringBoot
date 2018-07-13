package com.evan.blog.repository;

import com.evan.blog.domain.Category;

public interface CategoryDao {
    void insertCategory(Category category);
    Category selectCategoryById(int id);
    void deleteCategory(int id);
}
