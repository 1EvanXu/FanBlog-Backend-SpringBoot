package com.evan.blog.service.impls;

import com.evan.blog.model.Category;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.service.CategoryService;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.selectCategories();
    }

    @Override
    public List<Category> findCategoriesByName(String name) {
        return categoryDao.selectCategoriesByName(name);
    }

    @Override
    public void addCategoryIfAbsent(Category category) {
        if (category.getId() == null) {
            categoryDao.insertCategory(category);
        }
    }

    @Override
    @Transactional
    public boolean removeCategories(List<Integer> categoryIds) {
        try {
            for (Integer id: categoryIds) {
                categoryDao.deleteCategory(id);
            }
        } catch (SqlSessionException e) {
            return false;
        }
        return true;

    }
}
