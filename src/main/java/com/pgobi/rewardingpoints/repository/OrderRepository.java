package com.pgobi.rewardingpoints.repository;

import com.pgobi.rewardingpoints.entity.Cart;
import com.pgobi.rewardingpoints.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findOrderById(Long id);
    List <Order> findOrdersByCustomerId(Long customerId);

}
