package com.sanapp.sms.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class MistriChartDomain extends ChartDomain{

    @Id
    private long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;
}
