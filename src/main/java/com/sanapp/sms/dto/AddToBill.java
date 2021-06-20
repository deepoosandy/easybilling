package com.sanapp.sms.dto;

import lombok.Data;

@Data
public class AddToBill {

private String itemCode;
private int itemQuantity;
private double itemUnitPrice;
private String itemSum;
private String itemName;
private int rowNumber;
private  String measurementCode;


}
