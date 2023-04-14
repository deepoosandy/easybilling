package com.sanapp.sms.repository;

import com.sanapp.sms.domain.BuildingExpenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuildingOtherExpenseRepository extends JpaRepository<BuildingExpenses, Long> {
   // @Query(value = "  SELECT  SUM(expense_Amount) - sum(unpaid_amount) FROM building_expenses", nativeQuery = true)
   @Query(value = "  SELECT  SUM(expense_Amount) FROM building_expenses", nativeQuery = true)
    Double sumOfExpense();
}
