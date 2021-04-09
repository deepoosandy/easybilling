package com.sanapp.sms.dto;

import lombok.Data;

@Data
public class ShopDetailsDto {
    private String gstIN;
    private String shopAddress;
    private String state;
    private String district;
    private String phoneNumber1;
    private String phoneNumber2;
    private String shopName;
    private String shopDescription;
    private String pinCode;
    private String bankName;
    private String bankAccountNumber;
    private String bankIfscCode;
    private String invoicePre;
}
