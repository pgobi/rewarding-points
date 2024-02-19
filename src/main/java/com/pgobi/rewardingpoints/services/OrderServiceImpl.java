package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.controller.OrdersController;
import com.pgobi.rewardingpoints.entity.Cart;
import com.pgobi.rewardingpoints.entity.Order;
import com.pgobi.rewardingpoints.entity.Product;
import com.pgobi.rewardingpoints.model.OrderResponse;
import com.pgobi.rewardingpoints.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

	protected final static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	TransactionService transactionService;

	@Autowired
	CartService cartService;

	@Autowired
	ProductService productService;

	@Override
	public OrderResponse addOrderAndCartsAndTransaction(Long customerId, List<Long> productIds, List<Integer> quantities){
		OrderResponse orderResponse= new OrderResponse();
		String cartNumber = generateCartRandomNumber();
		Order order= new Order();
		int amoun = 0;
		for (int i = 0; i < productIds.size(); i++) {
			amoun = amoun + getAmountRoundingForPrice(productIds.get(i), quantities.get(i));
			logger.debug("[OrderServiceImpl][addOrderAndCartsAndTransaction] amoun:" + amoun + " productIds: " +productIds.get(i) + " quantities: "+ quantities.get(i));
			cartService.addNewCart(cartNumber, productIds.get(i), quantities.get(i));
		}
		Long trnsactionId = transactionService.addTransaction(amoun);
		Long orderId = addOrder(cartNumber,customerId,trnsactionId);
		logger.debug("[OrderServiceImpl][addOrderAndCartsAndTransaction] orderId: " +  orderId + " trnsactionId:  " + trnsactionId + " cartNumber :" + cartNumber);
		updateCartsByCartNumber(cartNumber, orderId);
		orderResponse.setOrderid(orderId);
		orderResponse.setCartNumber(cartNumber);
		return orderResponse;
	}

	public List<Cart> updateCartsByCartNumber(String cartNumber, Long orderId) {
		return cartService.updateCartsByCartNumber(cartNumber, orderId);
	}

	private int getAmountRoundingForPrice(Long id, int quantity){
		Product product = productService.getProductById(id);
		double price = product.getPrice();
		int roundedPrice = (int) Math.ceil(price) * quantity;
		return roundedPrice;
	}

	private Long addOrder(String cartNumber, Long customerId,Long trnsactionId){
		Order order = new Order();
		LocalDateTime localDateTime = LocalDateTime.now();
		order.setCustomerId(customerId);
		order.setCartNumber(cartNumber);
		order.setTransactionId(trnsactionId);
		order.setOrderDate(localDateTime);
		order = orderRepository.saveAndFlush(order);
		return order.getId();
	}

	@Override
	public Order getOrderById(Long id){
		return orderRepository.findOrderById(id);
	}

	@Override
	public List <Order> getOrdersByCustomerId(Long customerId){
		return orderRepository.findOrdersByCustomerId(customerId);
	}


	public static String generateCartRandomNumber() {
		  	int length = 25;
			String characters = "abcdefghijklmopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			StringBuilder orderNumber = new StringBuilder();

			Random random = new Random();
			for (int i = 0; i < length; i++) {
				int index = random.nextInt(characters.length());
				orderNumber.append(characters.charAt(index));
			}

			return orderNumber.toString();
		}

}
