package com.sanapp.sms.utils;

public class InvoiceNumberGenerator {

    public static String createInvoiceNumber(int lastInvoiceNumber, String invoicePrefix){
         int currentInvoiceNumber=lastInvoiceNumber+1;
         return invoicePrefix+currentInvoiceNumber;
    }
}
