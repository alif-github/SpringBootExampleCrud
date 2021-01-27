package com.example.spring.demo.Service;

import com.example.spring.demo.Model.Product;
import com.example.spring.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    private static HashMap<Long, Product> products = new HashMap<>();
    private static HashMap<String, Long> idNameHashMap = new HashMap<>();

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAllProducts() {
        //pagination should be added
        List<Product> products = productRepository.findAllProducts();
        return products;
    }

    @Override
    public Product findById(long id) {
        return products.get(id);
    }

    @Override
    public Product findByName(String name) {
        if (idNameHashMap.get(name)!=null){
            return products.get(idNameHashMap.get(name));
        }
        return null;
    }

    @Override
    public boolean isProductExist(Product product) {
        return false;
    }

    @Override
    public void saveProduct(Product product) {
        synchronized (this){
//            products.put(product.getId(),product);
//            idNameHashMap.put(product.getName(),product.getId());
            productRepository.saveProduct(product);
        }
    }

    @Override
    public void deleteProductById(long id) {
        synchronized (this){
            idNameHashMap.remove(products.get(id).getName());
            products.remove(id);
        }
    }

    @Override
    public void deleteAllProduct() {
        products.clear();
    }

    @Override
    public void updateProduct(Product product) {
        synchronized (this){
            products.put(product.getId(),product);
            idNameHashMap.put(product.getName(),product.getId());
        }
    }
}
