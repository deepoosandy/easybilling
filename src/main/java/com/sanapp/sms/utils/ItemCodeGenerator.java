package com.sanapp.sms.utils;

import java.util.UUID;

public class ItemCodeGenerator {



    public static String generateItemCode(){
        return UUID.randomUUID().toString();
    }
}
