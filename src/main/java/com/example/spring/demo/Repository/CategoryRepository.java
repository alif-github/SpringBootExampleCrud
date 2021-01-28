package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAllCategory();

    Category findById(int categoryId);

    List<Category> findByName(String categoryName);

    List<Category> findByIdAndName(int categoryId, String categoryName);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(int cateqoryId);

    void deleteCategoryByName(String categoryName);

    void deleteAllCategory();
}
