package com.sanapp.sms.controller;

import com.sanapp.sms.dto.ShopDetailsDto;
import com.sanapp.sms.services.IShopDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShopDetailController {

    @Autowired
    private IShopDetailsService iShopDetailsService;

    @GetMapping(value = {"/firmsetup","/savefirmdetails"})
    public String viewfirmSetup(Model model) {
        ShopDetailsDto shopDetailsDto = iShopDetailsService.shopDetials();
        if (shopDetailsDto != null) {
            model.addAttribute("savedFirmDetails", shopDetailsDto);
            return "firmsetupView";
        } else {
            model.addAttribute("firmsetup", new ShopDetailsDto());
        }

        return "firmsetup";
    }

    @GetMapping(value = {"/editfirmDetails"})
    public String editFirmDetails(@ModelAttribute("shopDetails") ShopDetailsDto shopDetailsDto, Model model) {
        ShopDetailsDto dbShopDetails = iShopDetailsService.shopDetials();
        model.addAttribute("firmsetup", dbShopDetails);
        return "firmsetup";
    }

    @PostMapping(value = {"/savefirmdetails"})
    public String saveFirmSetup(@ModelAttribute("shopDetails") ShopDetailsDto shopDetailsDto, Model model) {
        ShopDetailsDto dbShopDetails = iShopDetailsService.saveShopDetails(shopDetailsDto);
        model.addAttribute("savedFirmDetails", dbShopDetails);
        return "firmsetupView";
    }


}
