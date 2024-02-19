package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.entity.Customer;


public interface CustomerService {
	Customer findCustomerById(Long id);
}
