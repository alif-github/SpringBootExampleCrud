package com.example.spring.demo.Service;

import com.example.spring.demo.Model.CartDetail;

import java.util.List;

public interface CartDetailService {

    List<CartDetail> findAllCartDetails();

    List<CartDetail> findAllCartDetailsSave();

    CartDetail findById(int idCartDetail);

    void saveCartDetail(CartDetail cartDetail);

    void deleteCartDetailById(int idCartDetail);

    void updateCartDetail(CartDetail cartDetail);

    void deleteAllCartDetail();
}
