package com.example.spring.demo.Controller;

import com.example.spring.demo.Model.Category;
import com.example.spring.demo.Service.CategoryService;
import com.example.spring.demo.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Category--------------------------------------------

    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> listAllCategory() {
        List<Category> categories = categoryService.findAllCategory();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // -------------------Retrieve Single Category------------------------------------------

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") int categoryId) {
        logger.info("Fetching Category with id {}", categoryId);
        Category category = categoryService.findById(categoryId);
        if (category == null) {
            logger.error("Category with id {} not found.", categoryId);
            return new ResponseEntity<>(new CustomErrorType("Category with id " + categoryId + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/categoryName/", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@RequestParam("categoryName") String categoryName) {
        logger.info("Fetching Category with name {}", categoryName);
        Category category = categoryService.findByName(categoryName);
        if (category == null) {
            logger.error("Category with name {} not found.", categoryName);
            return new ResponseEntity<>(new CustomErrorType("Category with categoryName " + categoryName + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/category/detail/", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@RequestParam("categoryId") int categoryId, @RequestParam("categoryName") String categoryName) {
        logger.info("Fetching Category with categoryId {} and categoryName {}", categoryId, categoryName);
        List<Category> categories = categoryService.findByIdAndName(categoryId, categoryName);
        if (categories.size() == 0) {
            logger.error("Category with categoryId {} and categoryName {} not found.", categoryId, categoryName);
            return new ResponseEntity<>(new CustomErrorType("Category with categoryId " + categoryId + " and categoryName " + categoryName + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // -------------------Create a Category-------------------------------------------

    @RequestMapping(value = "/category/", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        logger.info("Creating Category : {}", category);

        if (categoryService.isCategoryExist(category)) {
            logger.error("Unable to create. A Category with name {} already exist", category.getCategoryName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Category with name " + category.getCategoryName() + " already exist."), HttpStatus.CONFLICT);
        }
        categoryService.saveCategory(category);

        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    // -------------------Update a Category-------------------------------------------

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        logger.info("Updating Category with id {}", categoryId);

        Category currentCategory = categoryService.findById(categoryId);

        if (currentCategory == null) {
            logger.error("Unable to update. Category with id {} not found.", categoryId);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Category with id " + categoryId + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCategory.setCategoryName(category.getCategoryName());

        categoryService.updateCategory(currentCategory);
        return new ResponseEntity<>(currentCategory, HttpStatus.OK);
    }

    // ------------------- Delete a Category-----------------------------------------

    @RequestMapping(value = "/category/{categoryId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") int categoryId) {
        logger.info("Fetching & Deleting Category with id {}", categoryId);

        Category category = categoryService.findById(categoryId);
        if (category == null) {
            logger.error("Unable to delete. Category with id {} not found.", categoryId);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Category with id " + categoryId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryById(categoryId);
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/category/categoryName/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@RequestParam("categoryName") String categoryName) {
        logger.info("Fetching & Deleting Category with categoryName {}", categoryName);

        Category category = categoryService.findByName(categoryName);
        if (category == null) {
            logger.error("Unable to delete. Category with categoryName {} not found.", categoryName);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Category with categoryName "+categoryName+" not found."),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryByName(categoryName);
        logger.info("Berhasil di Hapus !");
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Products-----------------------------

    @RequestMapping(value = "/category/", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteAllCategory() {
        logger.info("Deleting All Products");

        categoryService.deleteAllCategory();
        return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
    }
}
