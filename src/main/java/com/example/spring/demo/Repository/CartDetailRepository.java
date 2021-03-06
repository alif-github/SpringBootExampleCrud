package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.CartDetail;

import java.util.List;

public interface CartDetailRepository {

    List<CartDetail> findAllCartDetails();

    List<CartDetail> findAllCartDetailsSave();

    CartDetail findById(int idCartDetail);

    void saveCartDetail(CartDetail cartDetail);

    void deleteCartDetailById(int idCartDetail);

    void updateCartDetail(CartDetail cartDetail);

    void deleteAllCartDetail();
}
