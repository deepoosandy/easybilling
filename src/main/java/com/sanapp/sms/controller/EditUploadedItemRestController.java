package com.sanapp.sms.controller;

import com.sanapp.sms.dto.ItemWrapper;
import com.sanapp.sms.services.IEditExistingItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EditUploadedItemRestController {

    @Autowired
    private IEditExistingItemService editExistingItemService;

    @GetMapping("/listitems")
    private List<ItemWrapper> listItems(@RequestParam(value = "q", required = false) String query ) {
       return editExistingItemService.listSearchedItem(query);
    }

}
