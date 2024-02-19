package com.pgobi.rewardingpoints.controller;

import com.pgobi.rewardingpoints.entity.Order;
import com.pgobi.rewardingpoints.enums.ExceptionMessage;
import com.pgobi.rewardingpoints.exception.RewardingPointsException;
import com.pgobi.rewardingpoints.model.OrderRequest;
import com.pgobi.rewardingpoints.model.OrderResponse;
import com.pgobi.rewardingpoints.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/orders")
public class OrdersController {

    protected final static Logger logger = LoggerFactory.getLogger(OrdersController.class);

    @Autowired
    private OrderService orderService;

    public OrdersController(final OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/{ornderId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOrderById(@PathVariable("ornderId") Long id){

       Order order= new Order();
        try {
            order = orderService.getOrderById(id);
        }catch (Exception e){
            logger.error("[CartsController][getOrderByOrderId] exception: " + e.getMessage());
            throw new RewardingPointsException(ExceptionMessage.ORDER_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<?> addOrder(HttpServletRequest request,
                                      @PathVariable("customerId") Long customerId,
                                      @RequestBody OrderRequest orderRequest){
        List<Long> productIds = orderRequest.getProductIds();
        List<Integer> quantities = orderRequest.getQuantities();

        logger.debug("[TransactionController][addOrder] : " +productIds + quantities );

        OrderResponse orderResponse = new OrderResponse();

        try {
        orderResponse = orderService.addOrderAndCartsAndTransaction(customerId,productIds,quantities);
        }catch (Exception e){
           logger.error("[TransactionController][addOrder] exception: " + e.getMessage());
            throw new RewardingPointsException(ExceptionMessage.SOMETHING_WENT_WRONG.getValue(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(orderResponse,HttpStatus.OK);
    }

}

