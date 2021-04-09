package com.sanapp.sms.services;

import com.sanapp.sms.dto.ListItemDetails;
import com.sanapp.sms.repository.IItemRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements IBillingService {

    @Autowired
    private IItemRepository itemRepository;

    @Override
    public ListItemDetails getItemByItemCode(String itemCode) {
       return ItemDetailsMapper.mapperListItemDetails(itemRepository.findByItemCode(itemCode));
    }
}
