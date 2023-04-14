package com.sanapp.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CementDetailsDTO {
    private int rowNum;
    private Integer numberOfBora;
    private String purchaseDate;
    private String paymentStatus;
    private Double ratePerBora;
    private String paymentDate;
    public CementDetailsDTO(){}
}
