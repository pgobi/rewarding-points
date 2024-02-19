package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.entity.Product;
import com.pgobi.rewardingpoints.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getProductById(Long id) {
		return productRepository.findProductById(id);
	}

}
