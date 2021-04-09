package com.sanapp.sms.services;

import com.sanapp.sms.domain.ItemDetailsMaster;
import com.sanapp.sms.dto.ListItemDetails;
import com.sanapp.sms.repository.IItemRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListItemServiceImpl implements IListItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Override
    public List<ListItemDetails> listAllItems() {
        List<ItemDetailsMaster> allItems = itemRepository.listAllItems();

        return allItems.stream().map(ItemDetailsMapper::mapperListItemDetails)
                .collect(Collectors.toList());
    }
}
