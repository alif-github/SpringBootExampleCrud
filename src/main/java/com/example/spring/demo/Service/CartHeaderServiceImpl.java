package com.example.spring.demo.Service;

import com.example.spring.demo.Model.CartHeader;
import com.example.spring.demo.Repository.CartHeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartHeaderService")
public class CartHeaderServiceImpl implements CartHeaderService {

    @Autowired
    CartHeaderRepository cartHeaderRepository;

    @Override
    public List<CartHeader> findAllCartHeaders() {
        //pagination should be added
        List<CartHeader> cartHeaders = cartHeaderRepository.findAllCartHeaders();
        return cartHeaders;
    }

    @Override
    public List<CartHeader> findAllCartHeadersSave() {
        List<CartHeader> cartHeaders = cartHeaderRepository.findAllCartHeadersSave();
        return cartHeaders;
    }

    @Override
    public CartHeader findById(int idCard) {
        CartHeader obj;
        try {
            obj = cartHeaderRepository.findById(idCard);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            obj = null;
        }
        return obj;
    }

    @Override
    public void saveCartHeader(CartHeader cartHeader) {
        cartHeaderRepository.saveCartHeader(cartHeader);
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
