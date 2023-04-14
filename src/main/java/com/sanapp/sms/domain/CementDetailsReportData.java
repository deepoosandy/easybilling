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
public class CementDetailsReportData {

    @Id
    private long id;

    @Column(name = "amount")
    private Double amount;
    @Column(name = "item_unit_rate")
    private Double itemUnitRate;
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    @Column(name = "cement_quantity")
    private int  cementQuantity;
}
