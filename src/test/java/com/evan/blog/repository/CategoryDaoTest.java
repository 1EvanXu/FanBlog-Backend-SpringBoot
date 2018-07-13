package com.evan.blog.repository;

import com.evan.blog.domain.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        assertEquals("Java", category.getName());
    }

    @Test
    public void deleteCategory() {
        categoryDao.deleteCategory(5);
        Category category = categoryDao.selectCategoryById(5);
        assertNull(category);
    }
}