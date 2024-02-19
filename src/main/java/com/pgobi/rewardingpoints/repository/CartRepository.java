package com.pgobi.rewardingpoints.repository;

import com.pgobi.rewardingpoints.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findCartsById(Long id);
    List<Cart> findCartsByCartNumber(String cartNumber);
}
