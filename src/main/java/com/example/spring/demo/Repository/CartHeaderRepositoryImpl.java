package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.CartHeader;
import com.example.spring.demo.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cartHeaderRepository")
public class CartHeaderRepositoryImpl implements CartHeaderRepository {
    Date today;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<CartHeader> findAllCartHeaders() {
        String sql = "select * from cartheader";
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                    new CartHeader(
                            rs.getInt("idCard"),
                            rs.getString("tglTransaksi"),
                            rs.getInt("idCustomer"),
                            rs.getString("status"),
                            null
                    ));
    }

    @Override
    public List<CartHeader> findAllCartHeadersSave() {
        String sql = "select * from cartheader order by idCard desc limit 1";
        return jdbcTemplate.query(sql,
                (rs, rowNum) ->
                    new CartHeader(
                            rs.getInt("idCard"),
                            rs.getString("tglTransaksi"),
                            rs.getInt("idCustomer"),
                            rs.getString("status"),
                            null
                    ));
    }

    @Override
    public CartHeader findById(int idCard) {
        String sql = "select * from cartheader where idCard="+idCard+"";
        CartHeader cartHeader = jdbcTemplate.queryForObject(sql,
                (rs, rowNum) ->
                        new CartHeader(
                                rs.getInt("idCard"),
                                rs.getString("tglTransaksi"),
                                rs.getInt("idCustomer"),
                                rs.getString("status"),
                                null
                        ));
        return cartHeader;
    }

    @Override
    public void saveCartHeader(CartHeader cartHeader) {
        jdbcTemplate.update("insert into cartheader(tglTransaksi, idCustomer, status) values (?,?,?)",
                new Date(), cartHeader.getIdCustomer(), cartHeader.getStatus());
        List<Product> products = cartHeader.getProdutcs();

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            jdbcTemplate.update("insert into cartdetail(idCard, id, qty) values (?,?,?)",
                    cartHeader.getIdCard(), products.get(i).getId(), product.getQty());
        }
    }

    @Override
    public void deleteCartHeaderById(int idCard) {

    }

    @Override
    public void updateCartHeader(CartHeader cartHeader) {

    }

    @Override
    public void deleteAllCartHeader() {

    }
}
