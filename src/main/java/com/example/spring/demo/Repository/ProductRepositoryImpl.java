package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Product> findAllProducts() {
        return jdbcTemplate.query("select * from product",
                (rs, rowNum) ->
                    new Product(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("categoryId"),
                            rs.getInt("harga")
                    ));
    }
}
