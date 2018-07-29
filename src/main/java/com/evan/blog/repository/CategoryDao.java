package com.evan.blog.repository;

import com.evan.blog.model.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    List<Category> selectCategories();
    List<Category> selectCategoriesByName(@Param("categoryName") String categoryName);
    List<Category> selectCategoriesOrderByIncludedArticles();
    Category selectCategoryById(Integer id);
    String selectCategoryNameById(Integer id);
    void insertCategory(Category category);
    void deleteCategory(Integer id);
}
