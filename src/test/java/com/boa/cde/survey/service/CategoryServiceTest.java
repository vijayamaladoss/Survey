package com.boa.cde.survey.service;

import com.boa.cde.survey.entity.Category;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
/**
 * @AutoConfigureTestDatabase is a Spring Boot annotation used in integration tests to configure and
 * customize the test database. By default, when running integration tests, Spring Boot creates an
 * in-memory H2 database for testing.
 * The replace = Replace.NONE parameter tells Spring Boot to use the specified database and not
 * replace it with the default in-memory database
 */
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    public void testGetAllCategorys() {
        Category category = new Category();
        category.setName("Test Category");
        category.setQuestions(new ArrayList<>());

        Category createdCategory = categoryService.createCategory(category);

        List<Category> categorys = categoryService.getAllCategories();

        assertEquals(1, categorys.size());
        assertEquals(createdCategory.getName(), categorys.get(0).getName());
    }

    @Test
    public void testCreateCategory() {
        Category category = new Category();
        category.setName("Test Category");
        category.setQuestions(new ArrayList<>());

        Category createdCategory = categoryService.createCategory(category);

        assertNotNull(createdCategory.getId());
        assertEquals(category.getName(), createdCategory.getName());
    }

    @Test
    public void testUpdateCategory() {
        Category category = new Category();
        category.setName("Test Category");
        category.setQuestions(new ArrayList<>());

        Category createdCategory = categoryService.createCategory(category);

        Category updatedCategory = new Category();
        updatedCategory.setName("Updated Category");
        categoryService.updateCategory(createdCategory.getId(), updatedCategory);

        Category retrievedCategory = categoryService.getCategoryById(createdCategory.getId());

        assertEquals(updatedCategory.getName(), retrievedCategory.getName());
    }


    @Test
    public void testDeleteCategory() {
        Category category = new Category();
        category.setName("Test Category");
        category.setQuestions(new ArrayList<>());

        Category createdCategory = categoryService.createCategory(category);

        categoryService.deleteCategoryById(createdCategory.getId());

        List<Category> categorys = categoryService.getAllCategories();

        assertEquals(0, categorys.size());
    }

}
