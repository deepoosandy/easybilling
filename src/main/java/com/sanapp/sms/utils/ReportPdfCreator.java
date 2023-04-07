package com.sanapp.sms.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sanapp.sms.dto.BuildingExpenseDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class ReportPdfCreator {


    private static Font COURIER = new Font(Font.FontFamily.COURIER, 20, Font.BOLD);
    private static Font COURIER_SMALL = new Font(Font.FontFamily.COURIER, 16, Font.BOLD);
    private static Font COURIER_SMALL_FOOTER = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);


    public static void generate(HttpServletResponse response, List<BuildingExpenseDTO> buildingExpenseDTOList,
                                String logoImgPath, int noOfColumns, Float[] logoImgScale,
                                String reportFileName, List<String> columnNames) throws DocumentException, IOException {

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();
       /* addLogo(document, logoImgPath, logoImgScale);
        addDocTitle(document, reportFileName);
        createTable(document, noOfColumns,buildingExpenseDTOList, columnNames);
        addFooter(document, reportFileName);*/


        Font fontTiltle = FontFactory.getFont(FontFactory.COURIER);
        fontTiltle.setSize(20);
        PdfPTable infoTable = new PdfPTable(2);
        infoTable.setWidthPercentage(100f);
        PdfPCell cell1 = new PdfPCell();
        cell1.setBackgroundColor(CMYKColor.LIGHT_GRAY);
        Font font1 = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        font1.setColor(CMYKColor.WHITE);
        cell1.setPhrase(new Phrase("Home Expense Report", font1));
        infoTable.addCell(cell1);

        cell1.setPhrase(new Phrase("Starting year" + "16/02/2023", font1));
        infoTable.addCell(cell1);

        cell1.setPhrase(new Phrase("Till the Date:" + LocalDateTime.now(), font1));
        infoTable.addCell(cell1);
        cell1.setPhrase(new Phrase("Total Expense:", font1));
        infoTable.addCell(cell1);
        document.add(infoTable);

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{3, 3, 3, 3, 3});
        table.setSpacingBefore(5);
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(CMYKColor.CYAN);

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.WHITE);

        cell.setPhrase(new Phrase("Expense Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Expense Date", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Item Rate", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Expense Amount", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unpaid Amount", font));
        table.addCell(cell);
        PdfPTable dataTable = new PdfPTable(5);
        for (BuildingExpenseDTO buildingExpenseDTO : buildingExpenseDTOList) {
            dataTable.addCell(buildingExpenseDTO.getExpenseDescription());
            dataTable.addCell(buildingExpenseDTO.getExpenseDate());
            dataTable.addCell(buildingExpenseDTO.getItemRate());
            dataTable.addCell(String.valueOf(buildingExpenseDTO.getUnpaidAmount()));

        }
        document.add(dataTable);
        document.add(table);
        document.close();
    }

    public static void generatePdfReport(HttpServletResponse response,
                                         String logoImgPath, List<BuildingExpenseDTO> buildingExpenseDTOList, int noOfColumns, Float[] logoImgScale,
                                         String reportFileName, List<String> columnNames, double total) {

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            addLogo(document, logoImgPath, logoImgScale);
            addDocTitle(document, reportFileName, total);
            createTable(document, noOfColumns, buildingExpenseDTOList, columnNames);
            addFooter(document, reportFileName);
            document.close();

        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void addLogo(Document document, String logoImgPath, Float[] logoImgScale) {
        try {
            Image img = Image.getInstance(logoImgPath);
            img.scalePercent(logoImgScale[0], logoImgScale[1]);
            img.setAlignment(Element.ALIGN_RIGHT);
            document.add(img);
        } catch (DocumentException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void addDocTitle(Document document, String reportFileName, double total) throws DocumentException {
        String localDateString = LocalDateTime.now().format(DateUtility.getDateFormatter());
        Paragraph p1 = new Paragraph();
        leaveEmptyLine(p1, 1);
        p1.add(new Paragraph(reportFileName, COURIER));
        p1.setAlignment(Element.ALIGN_CENTER);
        leaveEmptyLine(p1, 1);
        p1.add(new Paragraph("Report generated on " + localDateString, COURIER_SMALL));
        p1.add(new Paragraph("Report Total Expense: " + total, COURIER_SMALL));

        document.add(p1);

    }

    private static void createTable(Document document, int noOfColumns, List<BuildingExpenseDTO> buildingExpenseDTOList, List<String> columnNames) throws DocumentException {
        Paragraph paragraph = new Paragraph();
        leaveEmptyLine(paragraph, 3);
        document.add(paragraph);

        PdfPTable table = new PdfPTable(noOfColumns);

        for (int i = 0; i < noOfColumns; i++) {
            PdfPCell cell = new PdfPCell(new Phrase(columnNames.get(i)));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.CYAN);
            table.addCell(cell);
        }

        table.setHeaderRows(1);
        populateExpendeData(table, buildingExpenseDTOList);
        document.add(table);
    }

    private static void populateExpendeData(PdfPTable table, List<BuildingExpenseDTO> buildingExpenseDTOList) {

        for (BuildingExpenseDTO expenseDto : buildingExpenseDTOList) {

            table.setWidthPercentage(100);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            table.addCell(expenseDto.getExpenseDescription());
            table.addCell(expenseDto.getExpenseDate());
            table.addCell(expenseDto.getItemRate());
            table.addCell(String.valueOf(expenseDto.getExpenseAmount()));
            table.addCell(String.valueOf(expenseDto.getUnpaidAmount()));
        }

    }


    private static void addFooter(Document document, String reportFileName) throws DocumentException {
        Paragraph p2 = new Paragraph();
        leaveEmptyLine(p2, 3);
        p2.setAlignment(Element.ALIGN_MIDDLE);
        p2.add(new Paragraph("------------------------End Of " + reportFileName + "------------------------", COURIER_SMALL_FOOTER));

        document.add(p2);
    }

    private static void leaveEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }
}
