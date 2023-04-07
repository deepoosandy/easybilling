package com.sanapp.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CementDetailsDTO {
    private int numberOfBora;
    private LocalDateTime purchaseDate;
    private String paymentStatus;
    private int ratePerBora;
    private LocalDateTime paymentDate;
}
