package com.pgobi.rewardingpoints.repository;

import com.pgobi.rewardingpoints.entity.Customer;
import com.pgobi.rewardingpoints.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
}
