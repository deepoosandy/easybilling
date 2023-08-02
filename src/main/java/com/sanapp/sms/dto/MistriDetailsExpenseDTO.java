package com.sanapp.sms.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MistriDetailsExpenseDTO {
    private Double paymentAmount;
    private String paymentDate;
    private String paymentDescription;
    private int rowNum;
}
