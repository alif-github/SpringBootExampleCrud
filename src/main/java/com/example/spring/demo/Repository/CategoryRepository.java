package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> findAllCategory();

    Category findById(int id);

    List<Category> findByName(String name);

    List<Category> findByIdAndName(int id, String name);

    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById();

    Category isCategoryExist(Category category);
}
