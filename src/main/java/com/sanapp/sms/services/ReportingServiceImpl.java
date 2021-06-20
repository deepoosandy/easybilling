package com.sanapp.sms.services;

import com.sanapp.sms.domain.Invoice;
import com.sanapp.sms.dto.InvoiceDto;
import com.sanapp.sms.repository.IInvoiceRepository;
import com.sanapp.sms.utils.ItemDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportingServiceImpl implements IReportingService{

    @Autowired
    private IInvoiceRepository iInvoiceRepository;
    @Override
    public List<InvoiceDto> listAllInvoices() {
        List<Invoice> invoices= iInvoiceRepository.findAll();
        return invoices.stream()
                .map(ItemDetailsMapper::invoiceDomainToInvoiceDto).collect(Collectors.toList());

    }
}
