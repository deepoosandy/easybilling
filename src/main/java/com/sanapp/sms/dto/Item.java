package com.sanapp.sms.dto;

import lombok.Data;

@Data
public class Item {
    private String itemCode;
    private String itemName;
    private double itemUnitPrice;
    private String itemDescription;
    private String measurementUnit;
}
