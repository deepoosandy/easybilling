package com.sanapp.sms.controller;

import com.sanapp.sms.dto.ListItemDetails;
import com.sanapp.sms.services.IBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingRestController {

    @Autowired
    private IBillingService billingService;

    @GetMapping("/item/")
    public ListItemDetails itemForBilling(@RequestParam("itemCode") String itemCode){
       return billingService.getItemByItemCode(itemCode);
    }
}
