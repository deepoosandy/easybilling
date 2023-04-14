package com.sanapp.sms.services;

import com.itextpdf.text.DocumentException;
import com.sanapp.sms.domain.BuildingExpenses;
import com.sanapp.sms.domain.CementDetails;
import com.sanapp.sms.domain.CementDetailsReportData;
import com.sanapp.sms.domain.MistriPaymentDetails;
import com.sanapp.sms.dto.BuildingDashboardDTO;
import com.sanapp.sms.dto.BuildingExpenseDTO;
import com.sanapp.sms.dto.CementDetailsDTO;
import com.sanapp.sms.dto.MistriDetailsExpenseDTO;
import com.sanapp.sms.repository.IBuildingOtherExpenseRepository;
import com.sanapp.sms.repository.ICementDetailsReportDataRepository;
import com.sanapp.sms.repository.ICementExpenseRepository;
import com.sanapp.sms.repository.IMistriExpenseReposity;
import com.sanapp.sms.utils.DateUtility;
import com.sanapp.sms.utils.CementReportCreator;
import com.sanapp.sms.utils.MistriReportCreator;
import com.sanapp.sms.utils.ReportPdfCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements IBuildingService {

    @Autowired
    private IBuildingOtherExpenseRepository iBuildingOtherExpenseRepository;
    @Autowired
    private ICementExpenseRepository iCementExpenseRepository;

    @Autowired
    private IMistriExpenseReposity iMistriExpenseReposity;

    @Autowired
    private ICementDetailsReportDataRepository iCementDetailsReportDateRepository;

    @Value("${reportFileName}")
    private String reportFileName;

    @Value("${reportFileNameDateFormat}")
    private String reportFileNameDateFormat;

    @Value("${localDateFormat}")
    private String localDateFormat;

    @Value("${logoImgPath}")
    private String logoImgPath;

    @Value("${logoImgScale}")
    private Float[] logoImgScale;

    @Value("${currencySymbol:}")
    private String currencySymbol;

    @Value("${table_noOfColumns}")
    private int noOfColumns;


    @Value("${mistri_report_noOfColumns}")
    private int noOfMistriReportColumns;

    @Value("${cement_report_noOfColumns}")
    private int noOfCementReportColumns;

    @Value("${table.columnNames}")
    private List<String> columnNames;

    @Value("${table.mistriReportcolumnNames}")
    private List<String> mistriReportColumnNames;

    @Value("${table.cementReportcolumnNames}")
    private List<String> cementReportColumnNames;


    @Override
    public BuildingDashboardDTO populateDashboardDetails() {
        Double otherExpense = iBuildingOtherExpenseRepository.sumOfExpense() != null ? iBuildingOtherExpenseRepository.sumOfExpense() : 0;
        Double cementExpense = iCementExpenseRepository.sumOfEachDayExpense() != null ? iCementExpenseRepository.sumOfEachDayExpense() : 0;
        Double mistriExpense = iMistriExpenseReposity.sumMistriExpenses() != null ? iMistriExpenseReposity.sumMistriExpenses() : 0;
        Double sumOfAllExpense = Double.sum(mistriExpense, Double.sum(otherExpense, cementExpense));
        BuildingDashboardDTO buildingDashboardDTO = BuildingDashboardDTO.builder().otherExpenseTotal(otherExpense)
                .mistriExpenseTotal(mistriExpense).cementExpenseTotal(cementExpense).sumOfAllExpense(sumOfAllExpense).build();
        return buildingDashboardDTO;
    }


    private BuildingExpenseDTO buildExpenseDomainToBuildingExpenseDTO(BuildingExpenses buildingExpenses) {
        BuildingExpenseDTO buildingExpenseDTO = new BuildingExpenseDTO();
        buildingExpenseDTO.setExpenseAmount(buildingExpenses.getExpenseAmount());
        buildingExpenseDTO.setExpenseDate(DateUtility.localDateToString(buildingExpenses.getExpenseDate()));
        buildingExpenseDTO.setExpenseDescription(buildingExpenses.getExpenseDescription());
        buildingExpenseDTO.setItemRate(buildingExpenses.getItemRate());
        buildingExpenseDTO.setUnpaidAmount(buildingExpenses.getUnpaidAmount() != null ? buildingExpenses.getUnpaidAmount() : 0);
        return buildingExpenseDTO;
    }

    @Override
    public BuildingExpenseDTO saveOtherExpense(BuildingExpenseDTO buildingExpenseDTO) {
        BuildingExpenses buildingExpenses = new BuildingExpenses();
        buildingExpenses.setItemRate(buildingExpenseDTO.getItemRate());
        buildingExpenses.setExpenseDate(DateUtility.stringToLocaldate(buildingExpenseDTO.getExpenseDate()));
        buildingExpenses.setExpenseAmount(buildingExpenseDTO.getExpenseAmount());
        buildingExpenses.setExpenseDescription(buildingExpenseDTO.getExpenseDescription());
        buildingExpenses.setUnpaidAmount(buildingExpenseDTO.getUnpaidAmount());
        return buildExpenseDomainToBuildingExpenseDTO(iBuildingOtherExpenseRepository.save(buildingExpenses));
    }

    @Override
    public List<BuildingExpenseDTO> listAllBuildingExpenseDTO() {
        List<BuildingExpenses> buildingExpensesList = iBuildingOtherExpenseRepository.findAll();
        return buildingExpensesList.stream().sorted(
                (b1, b2) -> b2.getExpenseDate().compareTo(b1.getExpenseDate())
        ).map(this::buildExpenseDomainToBuildingExpenseDTO).collect(Collectors.toList());

    }

    private void enrichHttpResponse(HttpServletResponse response, String fileName) {
        response.setContentType("application/pdf");
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=" + getPdfNameWithDate(fileName);
        response.setHeader(headerkey, headervalue);
    }

    @Override
    public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
        enrichHttpResponse(response, "ExpenseReport");
        Double otherExpense = iBuildingOtherExpenseRepository.sumOfExpense() != null ? iBuildingOtherExpenseRepository.sumOfExpense() : 0;
        ReportPdfCreator.generatePdfReport(response, logoImgPath, listAllBuildingExpenseDTO(), noOfColumns, logoImgScale,
                reportFileName, columnNames, otherExpense);
    }

    @Override
    public void mistriReportPdf(HttpServletResponse response) throws DocumentException, IOException {
        enrichHttpResponse(response, "MistriReport");
        Double mistriTotalPayment = iMistriExpenseReposity.sumMistriExpenses() != null ? iMistriExpenseReposity.sumMistriExpenses() : 0;
        MistriReportCreator.generatePdfReport(response, logoImgPath, listMistriPaymentDetailsDTO(), noOfMistriReportColumns, logoImgScale,
                "MistriReport", mistriReportColumnNames, mistriTotalPayment);
    }

    @Override
    public MistriDetailsExpenseDTO saveMistriPayments(MistriDetailsExpenseDTO mistriDetailsExpenseDTO) {
        return mistriDomainToMistriDetailsExpenseDTO(iMistriExpenseReposity.save(mistriDetailsExpenseDTOTomistriDomain(mistriDetailsExpenseDTO)));
    }

    private MistriPaymentDetails mistriDetailsExpenseDTOTomistriDomain(MistriDetailsExpenseDTO mistriDetailsExpenseDTO) {
        MistriPaymentDetails mistriPaymentDetails = new MistriPaymentDetails();
        mistriPaymentDetails.setDescription(mistriDetailsExpenseDTO.getPaymentDescription());
        mistriPaymentDetails.setPaymentDate(DateUtility.stringToLocaldate(mistriDetailsExpenseDTO.getPaymentDate()));
        mistriPaymentDetails.setPaidAmount(mistriDetailsExpenseDTO.getPaymentAmount());
        return mistriPaymentDetails;
    }

    private MistriDetailsExpenseDTO mistriDomainToMistriDetailsExpenseDTO(MistriPaymentDetails mistriPaymentDetails) {
        MistriDetailsExpenseDTO mistriDetailsExpenseDTO = new MistriDetailsExpenseDTO();
        mistriDetailsExpenseDTO.setPaymentAmount(mistriPaymentDetails.getPaidAmount());
        mistriDetailsExpenseDTO.setPaymentDescription(mistriPaymentDetails.getDescription());
        mistriDetailsExpenseDTO.setPaymentDate(DateUtility.localDateToString(mistriPaymentDetails.getPaymentDate()));
        mistriDetailsExpenseDTO.setRowNum(Long.valueOf(mistriPaymentDetails.getPaymentId()).intValue());
        return mistriDetailsExpenseDTO;
    }

    private String getPdfNameWithDate(String reportFileName) {
        String localDateString = LocalDateTime.now().format(DateUtility.getDateFormatter());
        return reportFileName + "-" + localDateString + ".pdf";
    }

    @Override
    public List<MistriDetailsExpenseDTO> listMistriPaymentDetailsDTO() {
        List<MistriPaymentDetails> buildingExpensesList = iMistriExpenseReposity.findAll();
        return buildingExpensesList.stream().sorted(
                (b1, b2) -> b2.getPaymentDate().compareTo(b1.getPaymentDate())
        ).map(this::mistriDomainToMistriDetailsExpenseDTO).collect(Collectors.toList());

    }

    @Override
    public void cementReportPdf(HttpServletResponse response) throws DocumentException, IOException {
        enrichHttpResponse(response, "CementReport");
        Double totalExpenseOnCementPurchase = iCementExpenseRepository.sumOfEachDayExpense() != null ? iCementExpenseRepository.sumOfEachDayExpense() : 0;
        CementReportCreator.generatePdfReport(response, logoImgPath, listCementDetailsDTO(), noOfCementReportColumns, logoImgScale,
                "CementReport", cementReportColumnNames, totalExpenseOnCementPurchase);
    }

    @Override
    public CementDetailsDTO saveCementDetails(CementDetailsDTO cementDetailsDTO) {
        return cementDomainToCementDetailsDTO
                (iCementExpenseRepository.save(cementDtoToDomain(cementDetailsDTO)));
    }

    @Override
    public List<CementDetailsReportData> listCementDetailsDTO() {

        return iCementDetailsReportDateRepository.reportData().stream().sorted(
                (b1, b2) -> b2.getPurchaseDate().compareTo(b1.getPurchaseDate())
        ).collect(Collectors.toList());

    }

    private CementDetails cementDtoToDomain(CementDetailsDTO cementDetailsDTO) {
        CementDetails cementPurchaseDetails = new CementDetails();
        cementPurchaseDetails.setNumberOfBora(cementDetailsDTO.getNumberOfBora());
        cementPurchaseDetails.setRatePerBora(cementDetailsDTO.getRatePerBora().intValue());
        cementPurchaseDetails.setPurchaseDate(DateUtility.stringToLocaldate(cementDetailsDTO.getPurchaseDate()));
        cementPurchaseDetails.setPaymentStatus("P");
        cementPurchaseDetails.setPaymentDate(DateUtility.stringToLocaldate(cementDetailsDTO.getPurchaseDate()));
        return cementPurchaseDetails;
    }

    private CementDetailsDTO cementDomainToCementDetailsDTO(CementDetails cementPurchaseDetails) {
        CementDetailsDTO cementDetailsDTO = new CementDetailsDTO();
        cementDetailsDTO.setNumberOfBora(cementPurchaseDetails.getNumberOfBora());
        cementDetailsDTO.setRatePerBora(Double.valueOf(cementPurchaseDetails.getRatePerBora()));
        cementDetailsDTO.setPurchaseDate(DateUtility.localDateToString(cementPurchaseDetails.getPurchaseDate()));
        cementDetailsDTO.setPaymentStatus("P");
        cementDetailsDTO.setPaymentDate(DateUtility.localDateToString(cementPurchaseDetails.getPurchaseDate()));
        cementDetailsDTO.setRowNum(Long.valueOf(cementPurchaseDetails.getPurchaseId()).intValue());
        return cementDetailsDTO;
    }
}
