package com.sanapp.sms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDetail {
    private int rowNumber;
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private double itemUnitPrice;
    private LocalDateTime date;
    private String measurementUnit;
}
