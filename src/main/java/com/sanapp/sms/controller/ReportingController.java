package com.sanapp.sms.controller;

import com.sanapp.sms.dto.InvoiceDto;
import com.sanapp.sms.services.IReportingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ReportingController {

    @Autowired
    private IReportingService iReportingService;

    @GetMapping("/reporting")
    public String reportingLanding(){

        return "reportingMain";
    }

    @GetMapping("/listAllInvoices")
    @ResponseBody
    public List<InvoiceDto> listAllInvoices(){
      return  iReportingService.listAllInvoices();
    }

}
