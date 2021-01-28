package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    List<Product> findAllProductsSave();

    Product findById(int id);

    Product findByName(String name);

    List<Product> findByIdAndName(int id, String name);

    boolean isProductExist(Product product);

    void saveProduct(Product product);

    void deleteProductById(int id);

    void deleteProductByName(String name);

    void deleteAllProduct();

    Product updateProduct(Product currentProduct);
}
