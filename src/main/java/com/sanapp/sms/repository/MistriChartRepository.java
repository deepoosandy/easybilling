package com.sanapp.sms.repository;

import com.sanapp.sms.domain.MistriChartDomain;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MistriChartRepository extends JpaRepository<MistriChartDomain, Long> {
    @Query(value = "SELECT payment_id AS id, SUM(payment_amount) AS amount, payment_date AS date\n" +
            "FROM mistri_payment_details\n" +
            "WHERE year(payment_date) = ? GROUP BY payment_date", nativeQuery = true)
    List<MistriChartDomain> mistriData(String year);

}
