package com.sanapp.sms.services;

import com.sanapp.sms.dto.ItemDetail;
import com.sanapp.sms.repository.IItemRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ItemUploadServiceImpl implements IItemUploadService {

    @Autowired
    private IItemRepository itemRepository;

    @Override
    public String excelUploadItems(List<ItemDetail> itemDetails) {
        StringBuilder alreadyUploadedItems=new StringBuilder();
       String prefix="Please check line in the below table, this item is already exists in th system.\n Line Number # ";
        itemDetails.forEach(item->{
            String itemToInsert=item.getItemName().toUpperCase();
            com.sanapp.sms.domain.ItemDetailsMaster existingItem= itemRepository.findByItemCode(item.getItemCode());
            if(existingItem!=null){
                alreadyUploadedItems.append( item.getRowNumber()).append(", ");
            }
        });

        if(!StringUtils.isEmpty(alreadyUploadedItems.toString())){
            prefix=prefix.concat(alreadyUploadedItems.toString());
            return prefix;
        }

        return alreadyUploadedItems.toString();
    }

    @Override
    public boolean showIsItemInSystemOrNot() {
       List< com.sanapp.sms.domain.ItemDetailsMaster> itemsInSystem=itemRepository.findAll();
       if(itemsInSystem!=null && !itemsInSystem.isEmpty()){
           return false;
       }
        return true;
    }


    @Override
    public String uploadItems(List<ItemDetail> itemDetails) {


        itemRepository.saveAll(itemDetails.stream().map(ItemDetailsMapper::itemDetailsDTOToItemDetailsDomain).
                collect(Collectors.toList()));

        return null;
    }


}
