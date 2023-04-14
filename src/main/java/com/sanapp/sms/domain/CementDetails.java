package com.sanapp.sms.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NamedQuery(name = "CementDetails.findAll", query = "SELECT i FROM CementDetails i")
public class CementDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cement_purchase_id")
    private long purchaseId;

    @Column(name = "number_of_bora")
    private int numberOfBora;

    @Column(name = "date_of_purchase")
    private LocalDate purchaseDate;

    @Column(name = "status")
    private String paymentStatus;

    @Column(name = "rate_per_bora")
    private int ratePerBora;

    @Column(name = "payment_date")
    private LocalDate paymentDate;


}
