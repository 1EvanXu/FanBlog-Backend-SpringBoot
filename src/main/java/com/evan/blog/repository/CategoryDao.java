package com.evan.blog.repository;

import com.evan.blog.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    List<Category> selectCategories();
    List<Category> selectCategoriesByName(@Param("categoryName") String categoryName);
    void insertCategory(Category category);
    Category selectCategoryById(int id);
    void deleteCategory(int id);
}
