package com.evan.blog.respository;

import com.evan.blog.domain.Category;

public interface CategoryDao {
    void addCategory(Category category);
    Category getCategoryById(int id);
    void deleteCategoryById(int id);
}
