package com.sanapp.sms.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NamedQuery(name="BuildingExpenses.findAll", query="SELECT i FROM BuildingExpenses i")
public class BuildingExpenses implements Serializable {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name="expense_id")
    private long expenseId;

    @Column(name="item_rate")
    private String itemRate;

    @Column(name="expense_description")
    private String expenseDescription;

    @Column(name="expense_date")
    private LocalDateTime expenseDate;

    @Column(name="expense_amount")
    private Double expenseAmount;


    @Column(name="unpaid_amount")
    private Double unpaidAmount;

}
