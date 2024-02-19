package com.pgobi.rewardingpoints.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    public void testOrderCreation() {
        // Given
        Long id = 1L;
        String cartNumber = "12345";
        Long customerId = 2L;
        Long transactionId = 100L;
        LocalDateTime orderDate = LocalDateTime.now();

        // When
        Order order = new Order();
        order.setId(id);
        order.setCartNumber(cartNumber);
        order.setCustomerId(customerId);
        order.setTransactionId(transactionId);
        order.setOrderDate(orderDate);

        // Then
        assertNotNull(order);
        assertEquals(id, order.getId());
        assertEquals(cartNumber, order.getCartNumber());
        assertEquals(customerId, order.getCustomerId());
        assertEquals(transactionId, order.getTransactionId());
        assertEquals(orderDate, order.getOrderDate());
    }
}