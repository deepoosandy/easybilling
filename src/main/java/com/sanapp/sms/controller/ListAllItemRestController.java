package com.sanapp.sms.controller;

import com.sanapp.sms.dto.ListItemDetails;
import com.sanapp.sms.services.IListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListAllItemRestController {

    @Autowired
    private IListItemService listItemService;

    @GetMapping("/listallitesm")
    public List<ListItemDetails> listAllItems() {

        return listItemService.listAllItems();
    }
}
