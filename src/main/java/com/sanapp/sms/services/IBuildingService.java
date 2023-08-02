package com.sanapp.sms.services;

import com.itextpdf.text.DocumentException;
import com.sanapp.sms.domain.CementDetailsReportData;
import com.sanapp.sms.dto.BuildingDashboardDTO;
import com.sanapp.sms.dto.BuildingExpenseDTO;
import com.sanapp.sms.dto.CementDetailsDTO;
import com.sanapp.sms.dto.MasonDetailsExpenseDTO;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IBuildingService {
    BuildingDashboardDTO populateDashboardDetails();

    BuildingExpenseDTO saveOtherExpense(BuildingExpenseDTO buildingExpenseDTO);
    List<BuildingExpenseDTO > listAllBuildingExpenseDTO();
    void generatePdf(HttpServletResponse response) throws DocumentException, IOException;

    void masonReportPdf(HttpServletResponse response) throws DocumentException, IOException;
    MasonDetailsExpenseDTO saveMasonPayments(MasonDetailsExpenseDTO mistriDetailsExpenseDTO);
    List<MasonDetailsExpenseDTO> listMasonPaymentDetailsDTO();


    void cementReportPdf(HttpServletResponse response) throws DocumentException, IOException;
    CementDetailsDTO saveCementDetails(CementDetailsDTO mistriDetailsExpenseDTO);
    List<CementDetailsReportData> listCementDetailsDTO();

}
