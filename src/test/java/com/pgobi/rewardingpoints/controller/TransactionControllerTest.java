package com.pgobi.rewardingpoints.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.pgobi.rewardingpoints.entity.Transaction;
import com.pgobi.rewardingpoints.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransactionControllerTest {

    private TransactionService transactionService = mock(TransactionService.class);
    private TransactionController transactionController = new TransactionController(transactionService);

    @Test
    public void testGetTransactionById() {
        Long transactionId = 1L;
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(transactionId);

        when(transactionService.getAllByTransactionId(transactionId)).thenReturn(mockTransaction);

        ResponseEntity<Object> responseEntity = transactionController.getTransactionById(transactionId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockTransaction, responseEntity.getBody());
    }
}