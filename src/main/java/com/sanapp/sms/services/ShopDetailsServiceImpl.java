package com.sanapp.sms.services;

import com.sanapp.sms.domain.ShopDetailsMaster;
import com.sanapp.sms.dto.ShopDetailsDto;
import com.sanapp.sms.repository.IShopDetailsRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopDetailsServiceImpl implements IShopDetailsService {
    @Autowired
    private IShopDetailsRepository shopDetailsRepository;

    @Override
    public ShopDetailsDto shopDetials() {
        ShopDetailsMaster shopetailsMaster = shopDetailsRepository.findById(Long.valueOf(1)).get();
        ShopDetailsDto shopDetailsDto=new ShopDetailsDto();
        if(shopetailsMaster!=null ){
            shopDetailsDto= ItemDetailsMapper.domainToDto(shopetailsMaster);
        }
        return shopDetailsDto;
    }
}
