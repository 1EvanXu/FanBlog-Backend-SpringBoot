package com.evan.blog.service.impls;

import com.evan.blog.model.Category;
import com.evan.blog.model.QueryFilter;
import com.evan.blog.repository.CategoryDao;
import com.evan.blog.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryDao categoryDao;

    @Value("${blog.data.page.size}")
    private int pageSize;

    @Override
    public PageInfo<Category> getCategories(Integer pageIndex, QueryFilter queryFilter) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Category> categories = categoryDao.selectCategories(queryFilter);
        return new PageInfo<>(categories);
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
