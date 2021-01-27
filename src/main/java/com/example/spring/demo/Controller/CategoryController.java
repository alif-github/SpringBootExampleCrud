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

    // -------------------Retrieve All Products--------------------------------------------

    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> listAllCategory() {
        List<Category> categories = categoryService.findAllCategory();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    // -------------------Retrieve Single Product------------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCategory(@PathVariable("id") long id) {
        logger.info("Fetching Category with id {}", id);
        Category category = categoryService.findById(id);
        if (category == null) {
            logger.error("Category with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Category with id " + id + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    // -------------------Create a Product-------------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
        logger.info("Updating Category with id {}", id);

        Category currentCategory = categoryService.findById(id);

        if (currentCategory == null) {
            logger.error("Unable to update. Category with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentCategory.setName(category.getName());
        categoryService.updateCategory(currentCategory);
        return new ResponseEntity<>(currentCategory, HttpStatus.OK);
    }

    // ------------------- Delete a Product-----------------------------------------

    @RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Category with id {}", id);

        Category category = categoryService.findById(id);
        if (category == null) {
            logger.error("Unable to delete. Category with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Category with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryById(id);
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
