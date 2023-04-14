package com.sanapp.sms.controller;

import com.itextpdf.text.DocumentException;
import com.sanapp.sms.domain.CementDetailsReportData;
import com.sanapp.sms.dto.BuildingExpenseDTO;
import com.sanapp.sms.dto.CementDetailsDTO;
import com.sanapp.sms.dto.MistriDetailsExpenseDTO;
import com.sanapp.sms.services.IBuildingService;
import com.sanapp.sms.utils.SMSConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BuildingController {

    @Autowired
    private IBuildingService iBuildingService;

    private List<BuildingExpenseDTO> addToExpenseList= new ArrayList<>();

    private List<MistriDetailsExpenseDTO> addToMistriPaymentDetailsList= new ArrayList<>();


    private List<CementDetailsDTO> addToCementDetailList= new ArrayList<>();

    private int expenseListRowNumber;
    private int addToMistriPaymentDetailRowNumber;
    private int addToCementRowNumber;

    @GetMapping(value = {"/dashboard","/backdashborad"})
    public String buildingDashboard(Model model) {

        model.addAttribute("dashboardData", iBuildingService.populateDashboardDetails());
        return "constructionDashboard";
    }

    @GetMapping(value = {"/otherExpense"})
    public String otherExpense(@ModelAttribute("buildingExpense") BuildingExpenseDTO buildingExpenseDTO) {
        iBuildingService.saveOtherExpense(buildingExpenseDTO);
        return "constructionDashboard";
    }

    @GetMapping(value = {"/openOtherExpenseTable"})
    public String openDetailedOtherExpenseList() {
        return "listAllOtherBuildingExpenses";
    }

    @GetMapping(value = {"/addToExpenseForm"})
    public String openExpenseForm(Model model) {
        model.addAttribute("expenseDto", new BuildingExpenseDTO());
        return "addBuildingExpense";
    }


    @GetMapping("/listAllOtherExpenses")
    @ResponseBody
    public List<BuildingExpenseDTO> listAllOtherExpenses() {
        return iBuildingService.listAllBuildingExpenseDTO();
    }

    @PostMapping("/addToExpense")
    public String addToExpense(@ModelAttribute(SMSConstants.ADD_TO_BILL) BuildingExpenseDTO expenseDTO, Model model) {

        if (addToExpenseList == null) {
            addToExpenseList = new ArrayList<>();
        }
        expenseListRowNumber++;
        expenseDTO.setRowNum(expenseListRowNumber);
        addToExpenseList.add(expenseDTO);
        model.addAttribute("expenseDto", new BuildingExpenseDTO());
        model.addAttribute("addedInExpenseList", addToExpenseList);
        return "addBuildingExpense";

    }

    @GetMapping("/deleteFromExpenseList")
    public String deleteFromExpenseList(@RequestParam("rowId") int rowNumber, Model model) {

        addToExpenseList.removeIf(x -> x.getRowNum() == rowNumber);
        expenseListRowNumber=0;
        //Again creating row number
        int recount = 0;
        for (BuildingExpenseDTO item : addToExpenseList) {
            recount++;
            item.setRowNum(recount);
        }

        model.addAttribute("expenseDto", new BuildingExpenseDTO());
        model.addAttribute("addedInExpenseList", addToExpenseList);

        return "addBuildingExpense";
    }

    @GetMapping("/saveExpense")
    public String saveExpense(Model model) {

        if (addToExpenseList != null) {
            addToExpenseList.stream().forEach(expense -> {
                iBuildingService.saveOtherExpense(expense);
            });
            addToExpenseList.clear();
            expenseListRowNumber=0;
        }
        model.addAttribute("expenseDto", new BuildingExpenseDTO());
        return "addBuildingExpense";

    }

    @GetMapping(value = "/expenseReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void generatePDF(HttpServletResponse response) throws IOException, DocumentException {
        iBuildingService.generatePdf(response);
    }

    /*End of expense section*/


    /*Start of Mistri section*/

    @GetMapping(value = {"/addToMistriForm"})
    public String openMistriForm(Model model) {
        model.addAttribute("mistriPaymentDto", new MistriDetailsExpenseDTO());
        return "addMistriPaymentDetails";
    }

    @PostMapping("/addToMistriPaymentDetails")
    public String addToMistriPaymentDetails(@ModelAttribute(SMSConstants.ADD_MISTRIPAYMENT_DETAILS) MistriDetailsExpenseDTO mistriDto, Model model) {

        if (addToMistriPaymentDetailsList == null) {
            addToMistriPaymentDetailsList = new ArrayList<>();
        }
        addToMistriPaymentDetailRowNumber++;
        mistriDto.setRowNum(addToMistriPaymentDetailRowNumber);
        addToMistriPaymentDetailsList.add(mistriDto);
        model.addAttribute("mistriPaymentDto", new MistriDetailsExpenseDTO());
        model.addAttribute("addedInMistriPaymentList", addToMistriPaymentDetailsList);
        return "addMistriPaymentDetails";

    }

    @GetMapping("/saveMistriPayment")
    public String saveMistriPaymentExpense(Model model) {

        if (addToMistriPaymentDetailsList != null) {
            addToMistriPaymentDetailsList.stream().forEach(mistriDetailsExpenseDTO -> {
                iBuildingService.saveMistriPayments(mistriDetailsExpenseDTO);
            });
            addToMistriPaymentDetailsList.clear();
            addToMistriPaymentDetailRowNumber=0;
        }
        model.addAttribute("mistriPaymentDto", new MistriDetailsExpenseDTO());
        return "addMistriPaymentDetails";

    }
    @GetMapping("/deleteFromMistriPaymentList")
    public String deleteFromMistriPaymentList(@RequestParam("rowId") int rowNumber, Model model) {

        addToMistriPaymentDetailsList.removeIf(x -> x.getRowNum() == rowNumber);
        addToMistriPaymentDetailRowNumber=0;
        //Again creating row number
        int recount = 0;
        for (MistriDetailsExpenseDTO dto : addToMistriPaymentDetailsList) {
            recount++;
            dto.setRowNum(recount);
        }

        model.addAttribute("mistriPaymentDto", new MistriDetailsExpenseDTO());
        model.addAttribute("addedInMistriPaymentList", addToMistriPaymentDetailsList);

        return "addMistriPaymentDetails";
    }


    @GetMapping("/listMistriPaymentDetails")
    @ResponseBody
    public List<MistriDetailsExpenseDTO> listMistriPaymentDetails() {
        return iBuildingService.listMistriPaymentDetailsDTO();
    }

    @GetMapping(value = {"/openMistriPaymentDetailsTable"})
    public String openMistriDetailsList() {
        return "listMistriPaymentDetails";
    }


    @GetMapping(value = "/mistriReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void generateMistriReportPDF(HttpServletResponse response) throws IOException, DocumentException {
        iBuildingService.mistriReportPdf(response);
    }

    /*End of Mistri section*/


    /*Start of Cement section*/
    @GetMapping(value = {"/addToCementForm"})
    public String openCementForm(Model model) {
        model.addAttribute("cementDto", new CementDetailsDTO());
        return "addCementDetails";
    }

    @PostMapping("/addToCementDetails")
    public String addToCementDetails(@ModelAttribute(SMSConstants.ADD_CEMENT_DETAILS) CementDetailsDTO cementDto, Model model) {

        if (addToCementDetailList == null) {
            addToCementDetailList = new ArrayList<>();
        }
        addToCementRowNumber++;
        cementDto.setRowNum(addToCementRowNumber);
        addToCementDetailList.add(cementDto);
        model.addAttribute("cementDto", new CementDetailsDTO());
        model.addAttribute("addToCementDetailList", addToCementDetailList);
        return "addCementDetails";

    }

    @GetMapping("/saveCementDetails")
    public String saveCementDetails(Model model) {

        if (addToCementDetailList != null) {
            addToCementDetailList.stream().forEach(cementDetailsDTO -> {
                iBuildingService.saveCementDetails(cementDetailsDTO);
            });
            addToCementDetailList.clear();
            addToCementRowNumber=0;
        }
        model.addAttribute("cementDto", new CementDetailsDTO());
        return "addCementDetails";

    }

    @GetMapping("/deleteFromCementList")
    public String deleteCementDetailsFromCementList(@RequestParam("rowId") int rowNumber, Model model) {

        addToCementDetailList.removeIf(x -> x.getRowNum() == rowNumber);
        addToCementRowNumber=0;
        //Again creating row number
        int recount = 0;
        for (CementDetailsDTO dto : addToCementDetailList) {
            recount++;
            dto.setRowNum(recount);
        }

        model.addAttribute("cementDto", new CementDetailsDTO());
        model.addAttribute("addToCementDetailList", addToCementDetailList);

        return "addCementDetails";
    }

    @GetMapping("/listCementDetails")
    @ResponseBody
    public List<CementDetailsReportData> listCementDetails() {
        return iBuildingService.listCementDetailsDTO();
    }

    @GetMapping(value = {"/openCementDetailsTable"})
    public String openCementDetailsList() {
        return "listCementDetails";
    }

    @GetMapping(value = "/cementReport", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void generateCementReportPDF(HttpServletResponse response) throws IOException, DocumentException {
        iBuildingService.cementReportPdf(response);
    }


    /*End of Cement section*/
}
