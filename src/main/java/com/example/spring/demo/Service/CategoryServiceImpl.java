package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Category;
import com.example.spring.demo.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    //Using two hashmap in order to provide performance of 0(1) while fetching products
    private static HashMap<Long, Category> categories = new HashMap<>();
    private static HashMap<String, Long> idNameHashMap = new HashMap<>();

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findById(int categoryId) {
        Category objCategory;
        try {
            objCategory = categoryRepository.findById(categoryId);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            objCategory = null;
        }
        return objCategory;
    }

    @Override
    public Category findByName(String categoryName) {
//        if (idNameHashMap.get(name) != null) {
//            return categories.get(idNameHashMap.get(name));
//        }
        Category objCategory = (Category) categoryRepository.findByName(categoryName).get(0);
        return objCategory;
    }

    @Override
    public List<Category> findByIdAndName(int categoryId, String categoryName) {
        return categoryRepository.findByIdAndName(categoryId, categoryName);
    }

    @Override
    public void saveCategory(Category category) {
        synchronized (this) { //Critical section synchronized
//            categories.put(category.getId(), category);
//            idNameHashMap.put(category.getName(), category.getId());
            categoryRepository.saveCategory(category);
        }
    }

    @Override
    public void updateCategory (Category category){
        synchronized (this) {
//            categories.put(category.getId(), category);
//            idNameHashMap.put(category.getName(), category.getId());
            categoryRepository.updateCategory(category);
        }
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        synchronized (this) {
            categoryRepository.deleteCategoryById(categoryId);
        }
    }

    @Override
    public void deleteCategoryByName(String categoryName) {
        synchronized (this) {
            categoryRepository.deleteCategoryByName(categoryName);
        }
    }

    @Override
    public List<Category> findAllCategory () {
        //pagination should be added
        List<Category> categories = categoryRepository.findAllCategory();
        return categories;
    }

    @Override
    public void deleteAllCategory () {
        categoryRepository.deleteAllCategory();
    }

    @Override
    public boolean isCategoryExist (Category category){
        return categoryRepository.findByName(category.getCategoryName()).size() != 0;
    }
}
