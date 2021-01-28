package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Category;

import java.util.List;

public interface CategoryService {

    Category findById(int categoryId);

    Category findByName(String categoryName);

    List<Category> findByIdAndName(int categoryId, String categoryName);

    void saveCategory(Category category);

    void updateCategory(Category currentCategory);

    void deleteCategoryById(int categoryId);

    void deleteCategoryByName(String categoryName);

    List<Category> findAllCategory();

    void deleteAllCategory();

    boolean isCategoryExist(Category category);
}
