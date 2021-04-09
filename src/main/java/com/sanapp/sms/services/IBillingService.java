package com.sanapp.sms.services;

import com.sanapp.sms.dto.ListItemDetails;

public interface IBillingService {
    ListItemDetails getItemByItemCode(String itemCode);
}
