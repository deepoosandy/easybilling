package com.sanapp.sms.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Setter
@Getter
public class CementChartDomain extends ChartDomain{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "date")
    private LocalDate date;
}
