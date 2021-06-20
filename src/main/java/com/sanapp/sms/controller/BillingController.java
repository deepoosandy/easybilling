package com.sanapp.sms.controller;

import com.sanapp.sms.domain.Invoice;
import com.sanapp.sms.domain.ShopDetailsMaster;
import com.sanapp.sms.dto.AddToBill;
import com.sanapp.sms.dto.InvoiceDto;
import com.sanapp.sms.dto.ListItemDetails;
import com.sanapp.sms.dto.ShopDetailsDto;
import com.sanapp.sms.repository.IInvoiceRepository;
import com.sanapp.sms.repository.IShopDetailsRepository;
import com.sanapp.sms.services.IBillingService;
import com.sanapp.sms.services.IShopDetailsService;
import com.sanapp.sms.utils.InvoiceNumberGenerator;
import com.sanapp.sms.utils.ItemDetailsMapper;
import com.sanapp.sms.utils.SMSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BillingController {


    private List<AddToBill> addToBillList;

    private int rowNumber;

    @Autowired
    private IBillingService billingService;

    @Autowired
    private IInvoiceRepository invoiceRepository;

    @Autowired
    private IShopDetailsRepository shopDetailsRepository;

    @Autowired
    private IShopDetailsService shopDetailsService;

    @GetMapping(value = {"/billing", "/addtobill"})
    public String billingLandingPage(Model model) {
        if (addToBillList != null) {
            addToBillList.clear();
        }

        model.addAttribute(SMSConstants.ADD_TO_BILL, new AddToBill());
        return "billing";
    }

    @PostMapping("/addtobill")
    public String addToBill(@ModelAttribute(SMSConstants.ADD_TO_BILL) AddToBill addToBill, Model model) {

        if (addToBillList == null) {
            addToBillList = new ArrayList<>();
        }
        rowNumber++;
        ListItemDetails listItemDetails = billingService.getItemByItemCode(addToBill.getItemCode());
        AddToBill populatedAddToBill = ItemDetailsMapper.mapAddToBill(listItemDetails);
        double sum = populatedAddToBill.getItemUnitPrice() * addToBill.getItemQuantity();
        populatedAddToBill.setItemSum(String.format("%,.2f", sum));
        populatedAddToBill.setItemQuantity(addToBill.getItemQuantity());
        populatedAddToBill.setRowNumber(rowNumber);
        addToBillList.add(populatedAddToBill);
        model.addAttribute(SMSConstants.ADD_TO_BILL, new AddToBill());
        model.addAttribute("addedInBill", addToBillList);

        return "billing";

    }

    @GetMapping("/delete")
    public String deleteFromBill(@RequestParam("rowId") int rowNumber, Model model) {
        addToBillList.removeIf(x -> x.getRowNumber() == rowNumber);

        //Again creating row number
        int recount = 0;
        for (AddToBill item : addToBillList) {
            recount++;
            item.setRowNumber(recount);
        }

        model.addAttribute(SMSConstants.ADD_TO_BILL, new AddToBill());
        model.addAttribute("addedInBill", addToBillList);

        return "billing";
    }

    @GetMapping(value = {"/next", "/printbill", "/backtobilling"})
    public String billingNextPage(Model model) {
        int lastInvoiceCount = invoiceRepository.lastBilledInvoice();
        List<ShopDetailsMaster> shopetailsMaster = shopDetailsRepository.findAll();
        String invoiceNumber = InvoiceNumberGenerator.createInvoiceNumber(lastInvoiceCount,
                shopetailsMaster.get(0).getInvoicePrefix());
        InvoiceDto invoiceDto = new InvoiceDto();
        invoiceDto.setInvoiceNumber(invoiceNumber);
        ItemDetailsMapper.populateInvoiceDto(addToBillList, invoiceDto);
        model.addAttribute("nextDetailsOfBill", invoiceDto);
        return "billingNext";
    }

    @GetMapping("/backtoprevious")
    private String backToPrevious(Model model) {

        model.addAttribute(SMSConstants.ADD_TO_BILL, new AddToBill());
        model.addAttribute("addedInBill", addToBillList);
        return "billing";
    }

   /* private void populateFromUI(InvoiceDto invoiceDto, InvoiceDto invoiceDtoFromUI){
        invoiceDto.setBillTo(invoiceDtoFromUI.getBillTo());
        invoiceDto.setPhoneNumber(invoiceDtoFromUI.getPhoneNumber());
        invoiceDto.setBillingAddress(invoiceDtoFromUI.getBillingAddress());
    }*/

    @PostMapping(value = {"/printbill"})
    public String generatePdf(Model model, @ModelAttribute("nextDetailsOfBill") InvoiceDto invoiceDtoFromUI) {

        ShopDetailsDto shopDetailsDto = shopDetailsService.shopDetials();
        ItemDetailsMapper.populateInvoiceDto(addToBillList, invoiceDtoFromUI);

        Invoice invoice = ItemDetailsMapper.InvoiceDtoToInvoiceDomain(invoiceDtoFromUI);
        String[] number = invoiceDtoFromUI.getInvoiceNumber().split(shopDetailsDto.getInvoicePre());
        invoice.setTotolInvoiceGenerated(Integer.parseInt(number[1]));
        invoiceRepository.save(invoice);
        model.addAttribute("shopDetails", shopDetailsDto);
        model.addAttribute("nextDetailsOfBill", invoiceDtoFromUI);

        return "invoice";
    }

}
