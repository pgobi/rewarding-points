package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.controller.TransactionController;
import com.pgobi.rewardingpoints.entity.Cart;
import com.pgobi.rewardingpoints.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService {

	protected final static Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
	@Autowired
	CartRepository cartRepository;

	@Override
	public Long addNewCart(String cartNumber, Long productId, int quantity) {
		Cart cart = new Cart();
		LocalDateTime localDateTime = LocalDateTime.now();
		cart.setProductId(productId);
		cart.setCartNumber(cartNumber);
		cart.setQuantity(quantity);
		cart.setCreatedDate(localDateTime);
		cart = cartRepository.saveAndFlush(cart);
		return cart.getId();
	}


	@Override
	public List <Cart> getCartsByCartNumber(String cartNumber){
		return cartRepository.findCartsByCartNumber(cartNumber);
	}


	@Override
	public List<Cart> updateCartsByCartNumber(String cartNumber, Long orderId) {
		List<Cart> carts = cartRepository.findCartsByCartNumber(cartNumber);
		for (Cart cart : carts) {
			cart.setOrderId(orderId);
		}
		return cartRepository.saveAll(carts);
	}

}
