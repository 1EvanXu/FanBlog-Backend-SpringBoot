package com.evan.blog.repository;

import com.evan.blog.model.Category;
import com.evan.blog.model.QueryFilter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryDao {
    List<Category> selectCategories(QueryFilter filter);
    List<Category> selectCategoriesByName(@Param("categoryName") String categoryName);
    List<Category> selectCategoriesOrderByIncludedArticles();
    Category selectCategoryById(long id);
    String selectCategoryNameById(long id);
    void insertCategory(Category category);
    void deleteCategory(long id);
}
