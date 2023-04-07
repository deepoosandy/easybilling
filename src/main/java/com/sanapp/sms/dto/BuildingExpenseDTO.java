package com.sanapp.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BuildingExpenseDTO {
    private String expenseDescription;
    private String itemRate;
    private String expenseDate;
    private Double expenseAmount;
    private Double unpaidAmount;
    private int rowNum;
public BuildingExpenseDTO(){}
}
