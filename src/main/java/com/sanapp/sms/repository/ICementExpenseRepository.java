package com.sanapp.sms.repository;

import com.sanapp.sms.domain.CementPurchaseDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICementExpenseRepository extends JpaRepository<CementPurchaseDetails, Long> {
    @Query(value = "\n" +
            "select SUM(total_perday) FROM\n" +
            " (SELECT number_of_bora*rate_per_bora AS total_perday FROM cement_details) AS totalCementExpense;", nativeQuery = true)
    Double sumOfEachDayExpense();
}
