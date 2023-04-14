package com.sanapp.sms.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ChartDomain {

    private Double amount;

    private LocalDate date;
}
