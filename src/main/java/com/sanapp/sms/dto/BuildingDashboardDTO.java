package com.sanapp.sms.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BuildingDashboardDTO {
    private double mistriExpenseTotal;
    private double otherExpenseTotal;
    private double cementExpenseTotal;
    private double sumOfAllExpense;
}
