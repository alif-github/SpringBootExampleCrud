package com.example.spring.demo.Controller;

import com.example.spring.demo.Model.CartDetail;
import com.example.spring.demo.Model.CartHeader;
import com.example.spring.demo.Model.Product;
import com.example.spring.demo.Service.CartDetailService;
import com.example.spring.demo.Service.CartHeaderService;
import com.example.spring.demo.Service.ProductService;
import com.example.spring.demo.Util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartDetailController {

    public static final Logger logger = LoggerFactory.getLogger(CartHeaderController.class);

    @Autowired
    CartDetailService cartDetailService;

    @Autowired
    CartHeaderService cartHeaderService;

    @Autowired
    ProductService productService;

    // -------------------Retrieve All Cart Detail--------------------------------------------

    @RequestMapping(value = "/cartDetail/", method = RequestMethod.GET)
    public ResponseEntity<List<CartDetail>> listAllCarts() {
        List<CartDetail> carts = (List<CartDetail>) cartDetailService.findAllCartDetails();
        if (carts.isEmpty()) {
            return new ResponseEntity<>(carts, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(carts, HttpStatus.OK);
    }

    // -------------------Retrieve Single cart detail------------------------------------------

    @RequestMapping(value = "/cartDetail/{idCartDetail}", method = RequestMethod.GET)
    public ResponseEntity<?> getCartDetail(@PathVariable("idCartDetail") int idCartDetail) {
        logger.info("Fetching cart with id {}", idCartDetail);
        CartDetail cartDetail = cartDetailService.findById(idCartDetail);
        if (cartDetail == null) {
            logger.error("cart with id {} not found.", idCartDetail);
            return new ResponseEntity<>(new CustomErrorType("cart with id " + idCartDetail  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartDetail, HttpStatus.OK);
    }

    // ------------------- Update a Product ------------------------------------------------

    @RequestMapping(value = "/cartDetail/{idCartDetail}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCartDetail(@PathVariable("idCartDetail") int idCartDetail, @RequestBody CartDetail cartDetail) {
        logger.info("Updating Cart with id {}", idCartDetail);

        CartDetail currentCartDetail = cartDetailService.findById(idCartDetail);

        if (currentCartDetail == null) {
            logger.error("Unable to update. Cart with id {} not found.", idCartDetail);
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Cart with id " + idCartDetail + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        CartHeader cart1 = cartHeaderService.findById(cartDetail.getIdCard());
        if (cart1 == null){
            logger.error("Unable to create. A Category Does Not exist");
            return new ResponseEntity<>(new CustomErrorType("Id Cart Not Found !"), HttpStatus.CONFLICT);
        }
        Product product1 = productService.findById(cartDetail.getId());
        if (product1 == null){
            logger.error("Unable to create. A Category Does Not exist");
            return new ResponseEntity<>(new CustomErrorType("Id Product Not Found !"), HttpStatus.CONFLICT);
        }

        currentCartDetail.setIdCard(cartDetail.getIdCard());
        currentCartDetail.setId(cartDetail.getId());
        currentCartDetail.setQty(cartDetail.getQty());
        cartDetailService.updateCartDetail(currentCartDetail);
        return new ResponseEntity<>(cartDetail, HttpStatus.CREATED);
    }

    // ------------------- Delete a Product-----------------------------------------

    @RequestMapping(value = "/cartDetail/{idCartDetail}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCartDetail(@PathVariable("idCartDetail") int idCartDetail) {
        logger.info("Fetching & Deleting Category with id {}", idCartDetail);

        CartDetail cartDetail = cartDetailService.findById(idCartDetail);
        if (cartDetail == null) {
            logger.error("Unable to delete. Cart with id {} not found.", idCartDetail);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Cart with id " + idCartDetail + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        cartDetailService.deleteCartDetailById(idCartDetail);
        return new ResponseEntity<CartDetail>(HttpStatus.NO_CONTENT);
    }


}