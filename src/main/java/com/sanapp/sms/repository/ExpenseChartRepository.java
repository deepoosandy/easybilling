
package com.sanapp.sms.repository;

import com.sanapp.sms.domain.CementChartDomain;
import com.sanapp.sms.domain.ExpenseChartDomain;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Scope("prototype")
public interface ExpenseChartRepository extends JpaRepository<ExpenseChartDomain, Long> {

    @Query(value = "SELECT expense_id AS id, SUM(expense_amount) AS amount, expense_date AS date\n" +
            "FROM building_expenses\n" +
            "WHERE year(expense_date) = ? GROUP BY expense_date", nativeQuery = true)
    List<ExpenseChartDomain> expenseData(String year);


}

