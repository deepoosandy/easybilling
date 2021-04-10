package com.sanapp.sms.utils;

import com.sanapp.sms.domain.*;
import com.sanapp.sms.dto.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemDetailsMapper {

    public static ItemDetailsMaster itemDetailsDTOToItemDetailsDomain(com.sanapp.sms.dto.ItemDetail item) {
        ItemDetailsMaster itemDetail = new ItemDetailsMaster();
        itemDetail.setItemCode(item.getItemCode());
        itemDetail.setItemDescription(item.getItemDescription());
        itemDetail.setItemName(item.getItemName());
        itemDetail.setUpLoadedDate(DateUtility.todaysDate());

        UnitPriceT itemUnitPrice = new UnitPriceT();
        itemUnitPrice.setUnitPrice(item.getItemUnitPrice());
        itemUnitPrice.setEffDt(DateUtility.todaysDate());
        itemUnitPrice.setItemDetailsMaster(itemDetail);
        itemUnitPrice.setThruDt(DateUtility.thruDate());
        itemUnitPrice.setMeasurementUnit(item.getMeasurementUnit());
        List<UnitPriceT> list = new ArrayList<>();
        list.add(itemUnitPrice);
        itemDetail.setUnitPriceTs(list);

        return itemDetail;
    }


    public static Item itemDetailsToItemDto(ItemDetailsMaster domainObj) {
        Item item = new Item();
        item.setItemCode(domainObj.getItemCode());
        item.setItemName(domainObj.getItemName());
        item.setItemDescription(domainObj.getItemDescription());
        Predicate<UnitPriceT> filter = unitItem -> (unitItem.getEffDt().isBefore(DateUtility.todaysDate()) ||
                unitItem.getEffDt().isEqual(DateUtility.todaysDate()))
                && (unitItem.getThruDt().isAfter(DateUtility.todaysDate()) || unitItem.getThruDt().isEqual(DateUtility.todaysDate()));
        domainObj.setUnitPriceTs(domainObj.getUnitPriceTs().stream().filter(filter).collect(Collectors.toList()));
        item.setItemUnitPrice(domainObj.getUnitPriceTs().get(0).getUnitPrice());
        item.setMeasurementUnit(domainObj.getUnitPriceTs().get(0).getMeasurementUnit());
        item.setItemId(domainObj.getId());
        return item;
    }

    public static ItemWrapper itemToItemWrapper(Item item) {
        ItemWrapper itemWrapper = new ItemWrapper();
        itemWrapper.setId(item.getItemCode());
        itemWrapper.setSlug(item.getItemUnitPrice());
        itemWrapper.setText(item.getItemName());
        return itemWrapper;
    }

    public static ListItemDetails mapperListItemDetails(ItemDetailsMaster domainObj) {
        ListItemDetails listItemDetails = new ListItemDetails();
        if (domainObj != null) {
            listItemDetails.setItemCode(domainObj.getItemCode());
            listItemDetails.setItemName(domainObj.getItemName());
            listItemDetails.setItemDescription(domainObj.getItemDescription());
            listItemDetails.setUploadedDate(DateUtility.dateInString(domainObj.getUpLoadedDate()));
            ItemUnitPrice itemUnitPrice = new ItemUnitPrice();
            Predicate<UnitPriceT> filter = unitItem -> (unitItem.getEffDt().isBefore(DateUtility.todaysDate()) ||
                    unitItem.getEffDt().isEqual(DateUtility.todaysDate()))
                    && (unitItem.getThruDt().isAfter(DateUtility.todaysDate()) || unitItem.getThruDt().isEqual(DateUtility.todaysDate()));
                   itemUnitPrice.setItemCode(domainObj.getItemCode());
            domainObj.getUnitPriceTs().stream().filter(filter).forEach(unitItem -> {
                itemUnitPrice.setEffDate(DateUtility.dateInString(unitItem.getEffDt()));
                itemUnitPrice.setThruDate(DateUtility.dateInString(unitItem.getThruDt()));
                itemUnitPrice.setItemUnitPrice(unitItem.getUnitPrice());
                itemUnitPrice.setMeasurementUnit(unitItem.getMeasurementUnit());
            });


            listItemDetails.setItemUnitPrice(itemUnitPrice);
        }

        return listItemDetails;
    }

    public static AddToBill mapAddToBill(ListItemDetails listItemDetails) {
        AddToBill addToBill = new AddToBill();
        if (listItemDetails.getItemCode() != null) {
            addToBill.setItemCode(listItemDetails.getItemCode());
            addToBill.setItemName(listItemDetails.getItemName());
            addToBill.setItemUnitPrice(listItemDetails.getItemUnitPrice().getItemUnitPrice());
        }
        return addToBill;
    }

    public static void populateInvoiceDto(List<AddToBill> addToBillList, InvoiceDto invoiceDto) {
        int totalItemsInBill = 0;
        double totalBillAmount = 0;
        if (addToBillList != null) {
            for (AddToBill billItem : addToBillList) {
                totalItemsInBill += billItem.getItemQuantity();
                totalBillAmount += billItem.getItemQuantity() * billItem.getItemUnitPrice();
            }
        }
        invoiceDto.setItemsForbill(addToBillList);
        invoiceDto.setTotalItemsInbill(totalItemsInBill);
        invoiceDto.setBillingTotalAmount(String.format("%,.2f", totalBillAmount));
        invoiceDto.setBillingDate(DateUtility.todaysDate());
    }

    public static ShopDetailsDto domainToDto(ShopDetailsMaster shopDetailsMaster) {
        ShopDetailsDto shopDetailsDto = new ShopDetailsDto();
        shopDetailsDto.setBankAccountNumber(shopDetailsMaster.getBankAccountNumber());
        shopDetailsDto.setBankIfscCode(shopDetailsMaster.getBankIfscNumber());
        shopDetailsDto.setBankName(shopDetailsMaster.getBankAccountName());
        shopDetailsDto.setDistrict(shopDetailsMaster.getDistrict());
        shopDetailsDto.setGstIN(shopDetailsMaster.getShopGstNumber());
        shopDetailsDto.setShopName(shopDetailsMaster.getShopName());
        shopDetailsDto.setShopDescription(shopDetailsMaster.getShopDescription());
        shopDetailsDto.setPhoneNumber1(shopDetailsMaster.getPhoneNumber1());
        shopDetailsDto.setPhoneNumber2(shopDetailsMaster.getPhoneNumber2());
        shopDetailsDto.setState(shopDetailsMaster.getState());
        shopDetailsDto.setInvoicePre(shopDetailsMaster.getInvoicePrefix());
        shopDetailsDto.setPinCode(shopDetailsMaster.getPincode());
        shopDetailsDto.setShopAddress(shopDetailsMaster.getShopAddress1());
        return shopDetailsDto;
    }

    public static UserDto domainToDto(ShopUser shopUser) {
        UserDto userDto = new UserDto();
        Optional<ShopUser> shopUserOptional = Optional.ofNullable(shopUser);
        shopUserOptional.ifPresent(su -> {
            userDto.setUsername(su.getUserName());
            userDto.setUsername(su.getUserName());
            userDto.setPassword(su.getPassword());
            userDto.setRole(su.getRole());
            userDto.setFirstName(su.getFirstName());
            userDto.setLastName(su.getLastName());
            userDto.setEnabled(su.getEnabled());
        });


        return userDto;
    }

    public static ShopUser dtoToDomain(UserDto userDto) {
        ShopUser shopUser = new ShopUser();
        shopUser.setUserName(userDto.getUsername());
        shopUser.setFirstName(userDto.getFirstName());
        shopUser.setLastName(userDto.getLastName());
        shopUser.setPassword(userDto.getPassword());
        shopUser.setRole(userDto.getRole());
        shopUser.setEnabled(userDto.getEnabled());
        return shopUser;
    }

    public static ItemDetailsMaster itemDtoToDomain(Item item) {
        com.sanapp.sms.dto.ItemDetail itemDetails = new ItemDetail();
        itemDetails.setItemDescription(item.getItemDescription());
        itemDetails.setItemName(item.getItemName());
        itemDetails.setItemCode(item.getItemCode());
        itemDetails.setDate(DateUtility.todaysDate());
        itemDetails.setMeasurementUnit(item.getMeasurementUnit());
        itemDetails.setItemUnitPrice(item.getItemUnitPrice());

        return itemDetailsDTOToItemDetailsDomain(itemDetails);
    }

    public static Measurement domainToDto(MeasurementMaster measurementMaster){
        Measurement measurement=new Measurement();
        measurement.setCode(measurementMaster.getMeasurementCode());
        measurement.setName(measurementMaster.getMeasurementName());

        return measurement;
    }

    public static ShopDetailsMaster dtoToDomain( ShopDetailsDto  shopDetailsDto){
        ShopDetailsMaster shopDetailsMaster =new ShopDetailsMaster();
        shopDetailsMaster.setBankAccountNumber(shopDetailsDto.getBankAccountNumber());
        shopDetailsMaster.setBankIfscNumber(shopDetailsDto.getBankIfscCode());
        shopDetailsMaster.setBankAccountName(shopDetailsDto.getBankName());
        shopDetailsMaster.setDistrict(shopDetailsDto.getDistrict());
        shopDetailsMaster.setShopGstNumber(shopDetailsDto.getGstIN());
        shopDetailsMaster.setShopName(shopDetailsDto.getShopName());
        shopDetailsMaster.setShopDescription(shopDetailsDto.getShopDescription());
        shopDetailsMaster.setPhoneNumber1(shopDetailsDto.getPhoneNumber1());
        shopDetailsMaster.setPhoneNumber2(shopDetailsDto.getPhoneNumber2());
        shopDetailsMaster.setState(shopDetailsDto.getState());
        shopDetailsMaster.setInvoicePrefix(shopDetailsDto.getInvoicePre());
        shopDetailsMaster.setPincode(shopDetailsDto.getPinCode());
        shopDetailsMaster.setShopAddress1(shopDetailsDto.getShopAddress());
        return shopDetailsMaster;
    }
}
