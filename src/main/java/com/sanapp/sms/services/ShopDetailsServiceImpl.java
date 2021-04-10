package com.sanapp.sms.services;

import com.sanapp.sms.domain.ShopDetailsMaster;
import com.sanapp.sms.dto.ShopDetailsDto;
import com.sanapp.sms.repository.IShopDetailsRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopDetailsServiceImpl implements IShopDetailsService {
    @Autowired
    private IShopDetailsRepository shopDetailsRepository;

    @Override
    public ShopDetailsDto shopDetials() {
        ShopDetailsDto shopDetailsDto = null;
        List<ShopDetailsMaster> shopDetails = shopDetailsRepository.findAll();
        if (shopDetails != null && !shopDetails.isEmpty()) {
            ShopDetailsMaster shopDetailsMaster = shopDetails.get(0);
            shopDetailsDto = ItemDetailsMapper.domainToDto(shopDetailsMaster);
        }

        return shopDetailsDto;
    }

    @Override
    public ShopDetailsDto saveShopDetails(ShopDetailsDto shopDetailsDto) {
        ShopDetailsMaster newshopDetailsMaster = ItemDetailsMapper.dtoToDomain(shopDetailsDto);
        ShopDetailsMaster shopDetailsMaster = null;
        List<ShopDetailsMaster> shopDetails = shopDetailsRepository.findAll();
        if (shopDetails != null && !shopDetails.isEmpty()) {
            shopDetailsMaster = shopDetails.get(0);
            newshopDetailsMaster.setId(shopDetailsMaster.getId());
        }
        shopDetailsMaster = shopDetailsRepository.save(newshopDetailsMaster);
        return ItemDetailsMapper.domainToDto(shopDetailsMaster);
    }
}
