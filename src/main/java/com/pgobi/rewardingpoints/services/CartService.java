package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.entity.Cart;

import java.util.List;

public interface CartService {
	Long addNewCart(String orderNumber, Long productId, int quantity);
	List <Cart> getCartsByCartNumber(String cartNumber);
	List<Cart> updateCartsByCartNumber(String cartNumber, Long orderId);

}
