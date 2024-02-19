package com.pgobi.rewardingpoints.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransactionTest {

    @Test
    public void testTransactionCreation() {
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setAmount(100);
        transaction.setCurrency("$");
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setRewardsPoints(10);

        assertEquals(1L, transaction.getId());
        assertEquals(100, transaction.getAmount());
        assertEquals("$", transaction.getCurrency());
        assertEquals(10, transaction.getRewardsPoints());
    }
}
