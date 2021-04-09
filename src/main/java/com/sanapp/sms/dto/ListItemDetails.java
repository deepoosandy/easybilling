package com.sanapp.sms.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListItemDetails {
    private String itemCode;
    private String itemName;
    private String itemDescription;
    private String uploadedDate;
    private ItemUnitPrice itemUnitPrice;

}
