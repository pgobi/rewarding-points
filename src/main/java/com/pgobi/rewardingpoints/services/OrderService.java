package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.entity.Order;
import com.pgobi.rewardingpoints.model.OrderResponse;

import java.util.List;

public interface OrderService {
	OrderResponse addOrderAndCartsAndTransaction(Long customerId, List<Long> productIds, List<Integer> quantities);
	Order getOrderById(Long id);
	List <Order> getOrdersByCustomerId(Long customerId);
}
