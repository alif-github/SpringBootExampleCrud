package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    //Using two hashmap in order to provide performance of 0(1) while fetching products
    private static HashMap<Long, Category> categories = new HashMap<>();
    private static HashMap<String, Long> idNameHashMap = new HashMap<>();

    @Override
    public Category findById(long id) {
        return categories.get(id);
    }

    @Override
    public Category findByName(String name) {
        if (idNameHashMap.get(name) != null) {
            return categories.get(idNameHashMap.get(name));
        }
        return null;
    }

    @Override
    public void saveCategory(Category category) {
        synchronized (this) { //Critical section synchronized
            categories.put(category.getId(), category);
            idNameHashMap.put(category.getName(), category.getId());
        }
    }

    @Override
    public void updateCategory (Category category){
        synchronized (this) {
            categories.put(category.getId(), category);
            idNameHashMap.put(category.getName(), category.getId());
        }
    }

    @Override
    public void deleteCategoryById ( long id){
        synchronized (this) {
            idNameHashMap.remove(categories.get(id).getName());
            categories.remove(id);
        }
    }

    @Override
    public List<Category> findAllCategory () {
        return new ArrayList<>(categories.values());
    }

    @Override
    public void deleteAllCategory () {
        categories.clear();
    }

    @Override
    public Category isCategoryExist (Category category){
        return findByName(category.getName());
    }
}
