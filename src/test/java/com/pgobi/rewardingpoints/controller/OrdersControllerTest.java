package com.pgobi.rewardingpoints.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pgobi.rewardingpoints.entity.Order;
import com.pgobi.rewardingpoints.model.OrderRequest;
import com.pgobi.rewardingpoints.model.OrderResponse;
import com.pgobi.rewardingpoints.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class OrdersControllerTest {

    private OrderService orderService = mock(OrderService.class);
    private OrdersController ordersController = new OrdersController(orderService);

    @Test
    public void testGetOrderById() {
        Long orderId = 1L;
        Order mockOrder = new Order();
        when(orderService.getOrderById(orderId)).thenReturn(mockOrder);

        ResponseEntity<Object> response = ordersController.getOrderById(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockOrder, response.getBody());
    }

    @Test
    public void testAddOrder() {
        Long customerId = 1L;
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProductIds(List.of(1L, 2L));
        orderRequest.setQuantities(List.of(2, 3));

        OrderResponse mockOrderResponse = new OrderResponse();
        when(orderService.addOrderAndCartsAndTransaction(customerId, orderRequest.getProductIds(), orderRequest.getQuantities()))
                .thenReturn(mockOrderResponse);

        ResponseEntity<?> response = ordersController.addOrder(null, customerId, orderRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockOrderResponse, response.getBody());
    }
}
