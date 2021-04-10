package com.sanapp.sms.services;

import com.sanapp.sms.domain.ItemDetailsMaster;
import com.sanapp.sms.dto.Item;
import com.sanapp.sms.dto.ItemWrapper;
import com.sanapp.sms.repository.IItemRepository;
import com.sanapp.sms.repository.IItemUnitPriceRepository;
import com.sanapp.sms.utils.DateUtility;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditExistingItemServiceImpl implements IEditExistingItemService {

    @Autowired
    private IItemRepository itemRepository;

    @Autowired
    private IItemUnitPriceRepository itemUnitPriceRepository;

    @Override
    public List<ItemWrapper> listSearchedItem(String query) {
        List<ItemDetailsMaster> domainItems = itemRepository.findAll();
        List<Item>items=null;
        if (StringUtils.isEmpty(query)) {
            items=  domainItems.stream().limit(15).map(ItemDetailsMapper::itemDetailsToItemDto).collect(Collectors.toList());

        }else {
            items= domainItems.stream().filter(item -> item.getItemName().toLowerCase().contains(query)).limit(15)
                    .map(ItemDetailsMapper::itemDetailsToItemDto)
                    .collect(Collectors.toList());
        }


       return items.stream().map(ItemDetailsMapper::itemToItemWrapper).collect(Collectors.toList());
    }

    @Override
    public Item fetchSearchedItem(String itemCode) {
        ItemDetailsMaster itemDetailsMaster= itemRepository.findByItemCode(itemCode);

        return ItemDetailsMapper.itemDetailsToItemDto(itemDetailsMaster);
    }

    @Override
    public Item saveItem(Item item) {
        ItemDetailsMaster itemMaster=  ItemDetailsMapper.itemDtoToDomain(item);
        itemMaster.setId(item.getItemId());
        itemRepository.save(itemMaster);
        return null;
    }

    @Override
    public Item updateExistingWithThruDate(Item item) {

        ItemDetailsMaster itemDetailsMaster=itemRepository.findByItemCode(item.getItemCode());
        itemDetailsMaster.getUnitPriceTs().stream().forEach(itemsUnitPrice->{
            itemsUnitPrice.setThruDt(DateUtility.todaysDate());
        });
        itemRepository.save(itemDetailsMaster);
        return null;
    }


}


