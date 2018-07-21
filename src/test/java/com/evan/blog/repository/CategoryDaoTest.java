package com.evan.blog.repository;

import com.evan.blog.model.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void insertCategory() {
        Category category = new Category("TypeScript");
        categoryDao.insertCategory(category);
    }

    @Test
    public void selectCategoryById() {
        Category category = categoryDao.selectCategoryById(2);
        System.out.println(category);
        assertEquals("Java", category.getName());
    }

    @Test
    public void selectCategoryNameById() {
        String categoryName = categoryDao.selectCategoryNameById(2);
        assertEquals("Java", categoryName);
    }

    @Test
    public void selectCategoriesByName() {
        List<Category> categories = categoryDao.selectCategoriesByName("Type");
        System.out.println(categories);
    }

    @Test
    public void selectCategories() {
        List<Category> categories = categoryDao.selectCategories();
        System.out.println(categories);
    }

    @Test
    public void deleteCategory() {
        categoryDao.deleteCategory(5);
        Category category = categoryDao.selectCategoryById(5);
        assertNull(category);
    }
}