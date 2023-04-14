package com.sanapp.sms.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NamedQuery(name="MistriPaymentDetails.findAll", query="SELECT i FROM MistriPaymentDetails i")
public class MistriPaymentDetails implements Serializable {

    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    @Column(name="payment_id")
    private long paymentId;

    @Column(name="payment_date")
    private LocalDate paymentDate;

    @Column(name="payment_amount")
    private double paidAmount;

    @Column(name="description_of_payment")
    private String description;

}
