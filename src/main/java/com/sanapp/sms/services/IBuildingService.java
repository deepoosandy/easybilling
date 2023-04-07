package com.sanapp.sms.services;

import com.itextpdf.text.DocumentException;
import com.sanapp.sms.dto.BuildingDashboardDTO;
import com.sanapp.sms.dto.BuildingExpenseDTO;
import com.sanapp.sms.dto.MistriDetailsExpenseDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IBuildingService {
    BuildingDashboardDTO populateDashboardDetails();
    BuildingExpenseDTO saveOtherExpense(BuildingExpenseDTO buildingExpenseDTO);
    List<BuildingExpenseDTO > listAllBuildingExpenseDTO();
    void generatePdf(HttpServletResponse response) throws DocumentException, IOException;
    void mistriReportPdf(HttpServletResponse response) throws DocumentException, IOException;
    MistriDetailsExpenseDTO saveMistriPayments(MistriDetailsExpenseDTO mistriDetailsExpenseDTO);
    List<MistriDetailsExpenseDTO> listMistriPaymentDetailsDTO();
}
