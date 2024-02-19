package com.pgobi.rewardingpoints.controller;

import com.pgobi.rewardingpoints.entity.Cart;
import com.pgobi.rewardingpoints.enums.ExceptionMessage;
import com.pgobi.rewardingpoints.exception.RewardingPointsException;
import com.pgobi.rewardingpoints.services.CartService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/carts")
public class CartsController {

    protected final static Logger logger = LoggerFactory.getLogger(CartsController.class);

    @Autowired
    private CartService cartservice;

    @GetMapping(value = "/{cartNumber}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getPointsByCartsId(@PathVariable("cartNumber") String cartNumber){

        List <Cart> carts;
        try {
            carts = cartservice.getCartsByCartNumber(cartNumber);
        }catch (Exception e){
            logger.error("[CartsController][getAllCarsByOrderId] exception: " + e.getMessage());
            throw new RewardingPointsException(ExceptionMessage.CART_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(carts,HttpStatus.OK);
    }

}

