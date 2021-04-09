package com.sanapp.sms.services;

import com.sanapp.sms.dto.ItemDetail;

import java.util.List;

public interface IItemUploadService {
     String uploadItems(List<ItemDetail> itemDetails);
     String excelUploadItems(List<ItemDetail> itemDetails);
     boolean showIsItemInSystemOrNot();
}
