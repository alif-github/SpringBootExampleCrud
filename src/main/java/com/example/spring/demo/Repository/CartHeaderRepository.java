package com.example.spring.demo.Repository;

import com.example.spring.demo.Model.CartHeader;

import java.util.List;

public interface CartHeaderRepository {

    List<CartHeader> findAllCartHeaders();

    List<CartHeader> findAllCartHeadersSave();

    CartHeader findById(int idCard);

    void saveCartHeader(CartHeader cartHeader);

    void deleteCartHeaderById(int idCard);

    void updateCartHeader(CartHeader cartHeader);

    void deleteAllCartHeader();
}
