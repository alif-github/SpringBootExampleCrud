package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("categoryRepository")
public class CategoryRepositoryImpl implements CategoryRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> findAllCategory() {
        return jdbcTemplate.query("select * from category",
                (rs, rowNum) ->
                    new Category(
                            rs.getInt("categoryId"),
                            rs.getString("categoryName")
                    ));
    }

    @Override
    public Category findById(int categoryId) {
        String sql = "select * from category where categoryId="+categoryId+"";
        return jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                    new Category(
                            rs.getInt("categoryId"),
                            rs.getString("categoryName")
                    ));
    }

    @Override
    public List<Category> findByName(String categoryName) {
        return jdbcTemplate.query("select * from category where categoryName like ?",
                new Object[]{"%"+categoryName+"%"},
                (rs, rowNum) ->
                    new Category(
                            rs.getInt("categoryId"),
                            rs.getString("categoryName")
                    ));
    }

    @Override
    public List<Category> findByIdAndName(int categoryId, String categoryName) {
        return jdbcTemplate.query("select * from category where categoryId like ? AND categoryName like ?",
                new Object[]{categoryId,"%"+categoryId+"%"},
                (rs, rowNum) ->
                    new Category(
                            rs.getInt("categoryId"),
                            rs.getString("categoryName")
                    ));
    }

    @Override
    public void saveCategory(Category category) {
        jdbcTemplate.update("insert into category(categoryName) values (?)",
                category.getCategoryName());
    }

    @Override
    public void updateCategory(Category currentCategory) {
        jdbcTemplate.update("UPDATE category\n" +
                "SET categoryName = ? " +
                "WHERE categoryId = ?;",
                currentCategory.getCategoryName(), currentCategory.getCategoryId());
    }

    @Override
    public void deleteCategoryById(int categoryId) {
        jdbcTemplate.execute("Delete from category where categoryId="+categoryId+"");
    }

    @Override
    public void deleteCategoryByName(String categoryName) {
        jdbcTemplate.execute("Delete from category where categoryName='"+categoryName+"'");
    }

    @Override
    public void deleteAllCategory() {
        jdbcTemplate.execute("delete from category");
    }


}
