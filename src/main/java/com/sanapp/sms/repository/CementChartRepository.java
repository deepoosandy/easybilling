
package com.sanapp.sms.repository;

import com.sanapp.sms.domain.CementChartDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CementChartRepository extends JpaRepository<CementChartDomain, Long> {
    @Query(value = "SELECT cement_purchase_id AS id, SUM(number_of_bora * rate_per_bora) AS amount, date_of_purchase AS date\n" +
            "FROM cement_details\n" +
            "WHERE year(date_of_purchase) =? GROUP BY date_of_purchase", nativeQuery = true)
    List<CementChartDomain> cementData(String year);

}

