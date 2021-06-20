package com.sanapp.sms.services;

import com.sanapp.sms.dto.InvoiceDto;

import java.util.List;

public interface IReportingService {
    List<InvoiceDto> listAllInvoices();
}
