package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAllProducts();

    Product findById(long id);

    Product findByName(String name);

    boolean isProductExist(Product product);

    void saveProduct(Product product);

    void deleteProductById(long id);

    void deleteAllProduct();

    void updateProduct(Product currentProduct);
}
