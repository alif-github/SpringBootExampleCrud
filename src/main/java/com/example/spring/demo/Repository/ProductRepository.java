package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    List<Product> findAllProductsSave();

    Product findById(int id);

    List<Product> findByName(String name);

    List<Product> findByIdAndName(int id, String name);

    void saveProduct(Product product);

    void deleteProductById(int id);

    void deleteProductByName(String name);

    void updateProduct(Product currentProduct);

    void deleteAllProduct();
}
