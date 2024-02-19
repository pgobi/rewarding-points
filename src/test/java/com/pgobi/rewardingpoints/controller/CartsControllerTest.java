package com.pgobi.rewardingpoints.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.pgobi.rewardingpoints.entity.Cart;
import com.pgobi.rewardingpoints.services.CartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@ExtendWith(MockitoExtension.class)
public class CartsControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartsController cartsController;

    @Test
    public void testGetPointsByCartsId() {
        String cartNumber = "12345";
        int quantity = 10;

        List<Cart> mockCarts = new ArrayList<>();
        Cart cart=  new Cart();
        cart.setId(1L);
        cart.setOrderId(1L);
        cart.setProductId(1l);
        cart.setCartNumber(cartNumber);
        cart.setQuantity(quantity);
        cart.setCreatedDate(LocalDateTime.now());
        mockCarts.add(cart);

        when(cartService.getCartsByCartNumber(cartNumber)).thenReturn(mockCarts);

        ResponseEntity<Object> response = cartsController.getPointsByCartsId(cartNumber);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockCarts, response.getBody());
    }
}
