package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.CartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cartDetailRepository")
public class CartDetailRepositoryImpl implements CartDetailRepository {
    Date today;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CartDetail> findAllCartDetails() {
        String sql = "select * from cartdetail";
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                    new CartDetail(
                            rs.getInt("idCartDetail"),
                            rs.getInt("idCard"),
                            rs.getInt("id"),
                            rs.getInt("qty")
                    ));
    }

    @Override
    public List<CartDetail> findAllCartDetailsSave() {
        String sql = "select * from cartdetail order by idCartDetail desc limit 1";
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                        new CartDetail(
                                rs.getInt("idCartDetail"),
                                rs.getInt("idCard"),
                                rs.getInt("id"),
                                rs.getInt("qty")
                        ));
    }

    @Override
    public CartDetail findById(int idCartDetail) {
        String sql = "select * from cartdetail where idCartDetail="+idCartDetail+"";
        CartDetail cartDetail = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                        new CartDetail(
                                rs.getInt("idCartDetail"),
                                rs.getInt("idCard"),
                                rs.getInt("id"),
                                rs.getInt("qty")
                        ));
        return cartDetail;
    }

    @Override
    public void saveCartDetail(CartDetail cartDetail) {
        jdbcTemplate.update("insert into cartdetail(idCard, id, qty) values (?,?,?)",
                cartDetail.getIdCard(), cartDetail.getId(), cartDetail.getQty());
    }

    @Override
    public void deleteCartDetailById(int idCartDetail) {

    }

    @Override
    public void updateCartDetail(CartDetail cartDetail) {
        jdbcTemplate.update("UPDATE cartdetail\n" +
                        "SET idCard = ?, id = ?, qty = ?" +
                        "WHERE idCartDetail = ?;",
                cartDetail.getIdCard(),cartDetail.getId(),cartDetail.getQty(),cartDetail.getIdCartDetail());
    }

    @Override
    public void deleteAllCartDetail() {

    }
}
