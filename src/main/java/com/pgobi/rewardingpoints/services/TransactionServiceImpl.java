package com.pgobi.rewardingpoints.services;

import com.pgobi.rewardingpoints.constants.Constants;
import com.pgobi.rewardingpoints.entity.Transaction;
import com.pgobi.rewardingpoints.model.ReportResponse;
import com.pgobi.rewardingpoints.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TransactionServiceImpl implements TransactionService {

	protected final static Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction getAllByTransactionId(Long id) {
		return transactionRepository.findAllById(id);
	}

	@Override
	public Long addTransaction(int amount){
		Transaction transaction = new Transaction();
		LocalDateTime localDateTime = LocalDateTime.now();
		transaction.setAmount(amount);
		transaction.setCurrency(Constants.defaultCurrency);
		transaction.setRewardsPoints(calculateRewardPoints(amount));
		transaction.setTransactionDate(localDateTime);
		transaction = transactionRepository.saveAndFlush(transaction);
		return transaction.getId();
	}

	@Override
	public List<ReportResponse> getAllTransactionsByLastThreeMonths() {
		List<ReportResponse> reportResponse = transactionRepository.findAllTransactionsByLastThreeMonths();
		return reportResponse;
	}

	@Override
	public List<ReportResponse> getTransactionsByCustomerIdLastThreeMonths(Long customerId) {
		List<ReportResponse> reportResponse = transactionRepository.findTransactionsByCustomerIdLastThreeMonths(customerId);
		return reportResponse;
	}

	private int calculateRewardPoints(int amount) {
		if (amount < Constants.amountFirstLevelReward + 1) {
			return 0;
		} else if ((amount > Constants.amountFirstLevelReward) && (amount <= Constants.amountSecondLevelReward)){
			return (amount - Constants.amountFirstLevelReward) * Constants.pointFirstLevelReward;
		} else if (amount > Constants.amountSecondLevelReward) {
			return ((amount - Constants.amountSecondLevelReward) * Constants.pointSecendLevelReward) + Constants.amountFirstLevelReward;
		}
		return 0;
	}

}
