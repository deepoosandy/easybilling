package com.sanapp.sms.repository;

import com.sanapp.sms.domain.MistriPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IMistriExpenseReposity extends JpaRepository<MistriPaymentDetails, Long> {
    @Query(value = "\n" +
            "SELECT SUM(payment_amount) FROM mistri_payment_details", nativeQuery = true)
    Double sumMistriExpenses();
}
