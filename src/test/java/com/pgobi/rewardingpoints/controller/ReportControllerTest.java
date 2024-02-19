package com.pgobi.rewardingpoints.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.pgobi.rewardingpoints.model.ReportResponse;
import com.pgobi.rewardingpoints.services.TransactionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ReportControllerTest {

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private ReportController reportController;

    @Test
    public void testGetTransactionsByCustomerIdLastThreeMonths() {
        Long customerId = 1L;
        List<ReportResponse> mockReportResponses = new ArrayList<>();
        when(transactionService.getTransactionsByCustomerIdLastThreeMonths(customerId)).thenReturn(mockReportResponses);

        ResponseEntity<Object> response = reportController.getTransactionsByCustomerIdLastThreeMonths(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReportResponses, response.getBody());
    }

    @Test
    public void testGetAllTransactionsByLastThreeMonths() {
        List<ReportResponse> mockReportResponses = new ArrayList<>();
        when(transactionService.getAllTransactionsByLastThreeMonths()).thenReturn(mockReportResponses);

        ResponseEntity<Object> response = reportController.getAllTransactionsByLastThreeMonths();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockReportResponses, response.getBody());
    }
}
