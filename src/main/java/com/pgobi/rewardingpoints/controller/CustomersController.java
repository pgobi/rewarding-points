package com.pgobi.rewardingpoints.controller;

import com.pgobi.rewardingpoints.enums.ExceptionMessage;
import com.pgobi.rewardingpoints.entity.Customer;
import com.pgobi.rewardingpoints.exception.RewardingPointsException;
import com.pgobi.rewardingpoints.services.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomersController {

    protected final static Logger logger = LoggerFactory.getLogger(CustomersController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCustomerById(@PathVariable("customerId") Long id){

        Customer customer = new Customer();

        try {
            customer = customerService.findCustomerById(id);
        }catch (Exception e){
            logger.error("[CartsController][getPointsByCustomerId] exception: " + e.getMessage());
            throw new RewardingPointsException(ExceptionMessage.CUSTOMETR_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customer,HttpStatus.OK);
    }
}

