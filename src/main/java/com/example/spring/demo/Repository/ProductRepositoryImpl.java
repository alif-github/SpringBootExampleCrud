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
    public List<Product> findAllProductsSave() {
        return jdbcTemplate.query("select * from product order by id desc limit 1",
                (rs, rowNum) ->
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("categoryId"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public Product findById(int id) {
        String sql = "select * from product where id="+id+"";
        Product product = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("categoryId"),
                                rs.getDouble("price")
                        ));
        return product;
    }

    @Override
    public List<Product> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM product where name like ?",
                new Object[]{"%"+name+"%"},
                (rs, rowNum) ->
                        new Product(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("categoryId"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public List<Product> findByIdAndName(int id, String name) {
        return jdbcTemplate.query("SELECT * FROM product where name like ? AND id like ?",
                new Object[]{name,"%"+id+"%"},
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

    @Override
    public void deleteProductById(int id) {

        jdbcTemplate.execute("DELETE FROM product WHERE id ="+id+"");
    }

    @Override
    public void deleteProductByName(String name) {
        jdbcTemplate.execute("DELETE FROM product WHERE name ='"+name+"'");
    }

    @Override
    public void updateProduct(Product currentProduct) {
        jdbcTemplate.update("UPDATE product\n" +
                "SET name = ?, categoryId = ?, price = ?" +
                "WHERE id = ?;",
                currentProduct.getName(),currentProduct.getCategoryId(),currentProduct.getPrice(),currentProduct.getId());
    }

    @Override
    public void deleteAllProduct() {
        jdbcTemplate.execute("delete from product" );
    }


}
