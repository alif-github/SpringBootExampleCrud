package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> findAllProducts();

    void saveProduct(Product product);
}
