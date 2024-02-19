package com.pgobi.rewardingpoints.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CartTest {

    @Test
    public void testCartCreation() {
        Cart cart = new Cart();
        assertNotNull(cart);
    }

    @Test
    public void testCartId() {
        Cart cart = new Cart();
        cart.setId(1L);
        assertEquals(1L, cart.getId());
    }

    @Test
    public void testCartProductId() {
        Cart cart = new Cart();
        cart.setProductId(100L);
        assertEquals(100L, cart.getProductId());
    }

    @Test
    public void testCartQuantity() {
        Cart cart = new Cart();
        cart.setQuantity(5);
        assertEquals(5, cart.getQuantity());
    }

    @Test
    public void testCartCartNumber() {
        Cart cart = new Cart();
        cart.setCartNumber("ABC123");
        assertEquals("ABC123", cart.getCartNumber());
    }

    @Test
    public void testCartOrderId() {
        Cart cart = new Cart();
        cart.setOrderId(10L);
        assertEquals(10L, cart.getOrderId());
    }

    @Test
    public void testCartCreatedDate() {
        Cart cart = new Cart();
        LocalDateTime now = LocalDateTime.now();
        cart.setCreatedDate(now);
        assertEquals(now, cart.getCreatedDate());
    }
}
