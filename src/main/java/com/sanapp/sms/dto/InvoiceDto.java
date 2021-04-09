package com.sanapp.sms.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class InvoiceDto {
    private String invoiceNumber;
    private String billTo;
    private LocalDateTime billingDate;
    private String phoneNumber;
    private String billingAddress;
    private String billingTotalAmount;
    private int totalItemsInbill;
    private List<AddToBill> itemsForbill;
}
