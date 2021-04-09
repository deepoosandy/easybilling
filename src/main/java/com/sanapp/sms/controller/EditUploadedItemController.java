package com.sanapp.sms.controller;

import com.sanapp.sms.domain.ItemDetailsMaster;
import com.sanapp.sms.dto.Item;
import com.sanapp.sms.dto.ItemList;
import com.sanapp.sms.services.IEditExistingItemService;
import com.sanapp.sms.services.IMeasurementService;
import com.sanapp.sms.utils.DateUtility;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class EditUploadedItemController {

    @Autowired
    private IEditExistingItemService editExistingItemService;

    @Autowired
    private IMeasurementService iMeasurementService;

    @PostMapping("/editselectedItem")
    public String editItem(@ModelAttribute("itemList") ItemList itemList, Model model) {
        if (itemList != null && !StringUtils.isEmpty(itemList.getItem())) {
            fetchItemByItemCode(itemList.getItem(),model);
            model.addAttribute("showForm", true);
            model.addAttribute("allMeasurements",iMeasurementService.listAllMeasurements());
        }

        return "editItem";
    }

    private void fetchItemByItemCode(String itemCode, Model model) {
            model.addAttribute("searchedItem", editExistingItemService.fetchSearchedItem(itemCode));
    }

    @PostMapping("/saveediteditem")
    public String saveEditedItem(@ModelAttribute("searchedItem") Item item, Model model) {
        Item savedItem =  editExistingItemService.fetchSearchedItem(item.getItemCode());

        //thru dating existing data.
        editExistingItemService.updateExistingWithThruDate(savedItem);

        //Updating exisiting Item with new data.
        editExistingItemService.saveItem(item);
        model.addAttribute("itemList", new ItemList());
        model.addAttribute("showForm", false);
        model.addAttribute("success", "Item updated successfully!");
        return "editItem";
    }


}
