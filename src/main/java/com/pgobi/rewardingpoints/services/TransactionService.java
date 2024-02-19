package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.entity.Transaction;
import com.pgobi.rewardingpoints.model.ReportResponse;

import java.util.List;

public interface TransactionService {
	Transaction getAllByTransactionId(Long trnsactionId);
	Long addTransaction(int amount);
	List<ReportResponse> getTransactionsByCustomerIdLastThreeMonths(Long customerId);
	List<ReportResponse> getAllTransactionsByLastThreeMonths();
}