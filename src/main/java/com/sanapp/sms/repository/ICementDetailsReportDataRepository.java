package com.sanapp.sms.repository;

import com.sanapp.sms.domain.CementDetailsReportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICementDetailsReportDataRepository extends JpaRepository<CementDetailsReportData, Long> {

    @Query(value = "SELECT cement_purchase_id AS id, SUM(number_of_bora*rate_per_bora) AS  amount, rate_per_bora AS item_unit_rate, \n" +
            "date_of_purchase AS purchase_date, SUM(number_of_bora) AS cement_quantity\n" +
            "FROM cement_details AS totalCementExpense GROUP BY date_of_purchase;", nativeQuery = true)
    List<CementDetailsReportData> reportData();



}
