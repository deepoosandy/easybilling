package com.sanapp.sms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MistriDetailsDTO {
    private LocalDateTime paymentDate;
    private double paidAmount;
}
