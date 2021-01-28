package com.example.spring.demo.Controller;

import com.example.spring.demo.Model.CartHeader;
import com.example.spring.demo.Service.CartHeaderService;
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
public class CartHeaderController {

    public static final Logger logger = LoggerFactory.getLogger(CartHeaderController.class);

    @Autowired
    CartHeaderService cartHeaderService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All CartHeader--------------------------------------------

    @RequestMapping(value = "/cart/", method = RequestMethod.GET)
    public ResponseEntity<List<CartHeader>> listAllCartHeader() {
        List<CartHeader> cartHeaders = cartHeaderService.findAllCartHeaders();
        if (cartHeaders.isEmpty()) {
            return new ResponseEntity<>(cartHeaders, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartHeaders, HttpStatus.OK);
    }

    // -------------------Retrieve Single CartHeader------------------------------------------

    @RequestMapping(value = "/cart/{idCart}", method = RequestMethod.GET)
    public ResponseEntity<?> getCartHeader(@PathVariable("idCart") int idCart) {
        logger.info("Fetching Cart with idCart {}", idCart);
        CartHeader cartHeader = cartHeaderService.findById(idCart);
        if (cartHeader == null) {
            logger.error("Cart with idCart {} not found.", idCart);
            return new ResponseEntity<>(new CustomErrorType("Cart with idCart " + idCart + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartHeader, HttpStatus.OK);
    }

    // -------------------Create a CartHeader-----------------------------------------------

    @RequestMapping(value = "/cart/", method = RequestMethod.POST)
    public ResponseEntity<?> createCartHeader(@RequestBody CartHeader cartHeader) {
        logger.info("Creating Cart : {}", cartHeader);

        cartHeaderService.saveCartHeader(cartHeader);
        List<CartHeader> cartHeaders = (List<CartHeader>) cartHeaderService.findAllCartHeadersSave();
        return new ResponseEntity<>(cartHeaders, HttpStatus.CREATED);
    }
}
