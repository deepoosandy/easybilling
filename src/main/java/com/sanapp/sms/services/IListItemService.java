package com.sanapp.sms.services;

import com.sanapp.sms.dto.ListItemDetails;

import java.util.List;

public interface IListItemService {
    List<ListItemDetails> listAllItems();
}
