package com.boa.cde.survey.service;

import com.boa.cde.survey.domain.Category;
import com.boa.cde.survey.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
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

        List<Category> categorys = categoryService.getAllCategorys();

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

        List<Category> categorys = categoryService.getAllCategorys();

        assertEquals(0, categorys.size());
    }

}
