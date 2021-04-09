package com.sanapp.sms.dto;

import lombok.Data;

import java.util.List;

@Data
public class BillingObject {
    private String invoiceNumber;
    private String invoiceDate;
    private double billTotal;
    private int billItem;
    List<AddToBill> itemsInBill;
    private String billingAddress;
    private String nameOnBill;
    private String billerPhoneNumber;

}
