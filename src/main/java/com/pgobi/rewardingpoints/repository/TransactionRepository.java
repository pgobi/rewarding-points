package com.pgobi.rewardingpoints.repository;

import com.pgobi.rewardingpoints.entity.Transaction;
import com.pgobi.rewardingpoints.model.ReportResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("SELECT new com.pgobi.rewardingpoints.model.ReportResponse(o.customerId, SUM(t.rewardsPoints)) " +
            " FROM Transaction t INNER JOIN Order o ON o.transactionId = t.id " +
            " WHERE t.transactionDate BETWEEN (CURRENT_DATE()-90) AND CURRENT_DATE() GROUP BY o.customerId")
    List<ReportResponse> findAllTransactionsByLastThreeMonths();

    @Query("SELECT new com.pgobi.rewardingpoints.model.ReportResponse(o.customerId, SUM(t.rewardsPoints)) " +
            " FROM Transaction t INNER JOIN Order o ON o.transactionId = t.id " +
            " WHERE o.customerId =?1 and t.transactionDate BETWEEN (CURRENT_DATE()-90) AND CURRENT_DATE() GROUP BY o.customerId")
    List<ReportResponse> findTransactionsByCustomerIdLastThreeMonths(Long customerId);

    Transaction findAllById(Long id);
}
