package com.example.spring.demo.Service;

import com.example.spring.demo.Model.CartDetail;
import com.example.spring.demo.Repository.CartDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartDetailService")
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Override
    public List<CartDetail> findAllCartDetails() {
        List<CartDetail> cartDetails = cartDetailRepository.findAllCartDetails();
        return cartDetails;
    }

    @Override
    public List<CartDetail> findAllCartDetailsSave() {
        List<CartDetail> cartDetails = cartDetailRepository.findAllCartDetailsSave();
        return cartDetails;
    }

    @Override
    public CartDetail findById(int idCartDetail) {
        CartDetail cartDetail;
        try {
            cartDetail = cartDetailRepository.findById(idCartDetail);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e);
            cartDetail = null;
        }
        return cartDetail;
    }

    @Override
    public void saveCartDetail(CartDetail cartDetail) {
        synchronized (this) {
            cartDetailRepository.saveCartDetail(cartDetail);
        }
    }

    @Override
    public void deleteCartDetailById(int idCartDetail) {
        synchronized (this) {
            cartDetailRepository.deleteCartDetailById(idCartDetail);
        }
    }

    @Override
    public void updateCartDetail(CartDetail cartDetail) {
        synchronized (this) {
            cartDetailRepository.updateCartDetail(cartDetail);
        }
    }

    @Override
    public void deleteAllCartDetail() {

    }
}

