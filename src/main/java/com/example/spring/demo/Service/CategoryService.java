package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Category;

import java.util.List;

public interface CategoryService {
    Category findById(long id);

    Category findByName(String name);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(long id);

    List<Category> findAllCategory();

    void deleteAllCategory();

    Category isCategoryExist(Category category);
}
