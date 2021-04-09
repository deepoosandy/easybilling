package com.sanapp.sms.services;

import com.sanapp.sms.dto.Item;
import com.sanapp.sms.dto.ItemWrapper;

import java.util.List;

public interface IEditExistingItemService {
    List<ItemWrapper> listSearchedItem(String query);

    Item fetchSearchedItem(String itemCode);

    Item saveItem(Item item);

    Item updateExistingWithThruDate(Item item);
}
