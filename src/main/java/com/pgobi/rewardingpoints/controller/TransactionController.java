package com.pgobi.rewardingpoints.controller;

import com.pgobi.rewardingpoints.entity.Transaction;
import com.pgobi.rewardingpoints.enums.ExceptionMessage;
import com.pgobi.rewardingpoints.exception.RewardingPointsException;
import com.pgobi.rewardingpoints.services.OrderService;
import com.pgobi.rewardingpoints.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    protected final static Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;

    public TransactionController(final TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(value = "/{transactionId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTransactionById(@PathVariable("transactionId") Long transactionId){

        Transaction transaction = new Transaction();

        try {
        transaction = transactionService.getAllByTransactionId(transactionId);
        }catch (Exception e){
            logger.error("[TransactionController][getTransactionById] exception: " + e.getMessage());
            throw new RewardingPointsException(ExceptionMessage.TRANSACTION_NOT_FOUND.getValue(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(transaction,HttpStatus.OK);
    }

}
