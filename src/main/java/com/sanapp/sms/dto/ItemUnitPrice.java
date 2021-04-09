package com.sanapp.sms.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemUnitPrice {
    private String itemCode;
    private String effDate;
    private String thruDate;
    private double itemUnitPrice;
    private String measurementUnit;
}
