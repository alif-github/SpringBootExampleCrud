package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productRepository")
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
                            rs.getDouble("price")
                    ));
    }

    @Override
    public void saveProduct(Product product) {
        jdbcTemplate.update("insert into product(name, categoryId, price) values (?,?,?)",
                product.getName(), product.getCategoryId(), product.getPrice());
    }
}
