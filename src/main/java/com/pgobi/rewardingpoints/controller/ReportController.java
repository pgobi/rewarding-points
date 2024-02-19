package com.pgobi.rewardingpoints.controller;

import com.pgobi.rewardingpoints.enums.ExceptionMessage;
import com.pgobi.rewardingpoints.exception.RewardingPointsException;
import com.pgobi.rewardingpoints.model.ReportResponse;
import com.pgobi.rewardingpoints.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    protected final static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/{customerId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getTransactionsByCustomerIdLastThreeMonths(@PathVariable("customerId") Long customerId){

        List<ReportResponse> reportResponses;

        try {
            reportResponses = transactionService.getTransactionsByCustomerIdLastThreeMonths(customerId);
        }catch (Exception e){
            logger.error("[ReportController][getTransactionsByCustomerIdLastThreeMonths] exception: " + e.getMessage());
            throw new RewardingPointsException(ExceptionMessage.SOMETHING_WENT_WRONG.getValue(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(reportResponses,HttpStatus.OK);
    }

    @GetMapping(value = "/",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllTransactionsByLastThreeMonths(){

        List<ReportResponse> reportResponses;

       try {
            reportResponses = transactionService.getAllTransactionsByLastThreeMonths();
       }catch (Exception e){
           logger.error("[ReportController][getAllTransactionsByLastThreeMonths] exception: " + e.getMessage());
           throw new RewardingPointsException(ExceptionMessage.SOMETHING_WENT_WRONG.getValue(), HttpStatus.BAD_REQUEST);
       }

        return new ResponseEntity<>(reportResponses,HttpStatus.OK);
    }

}
