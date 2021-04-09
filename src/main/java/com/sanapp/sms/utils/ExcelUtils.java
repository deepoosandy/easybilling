package com.sanapp.sms.utils;

import com.sanapp.sms.dto.ItemDetail;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtils {
    public static List<ItemDetail> parseExcelFile(InputStream is) throws IOException {
        Workbook workbook = new XSSFWorkbook(is);

        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();

        List<ItemDetail> itemDetails = new ArrayList<>();

        int rowNumber = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // skip header
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Iterator<Cell> cellsInRow = currentRow.iterator();

            ItemDetail itemDetail = new ItemDetail();

            int cellIndex = 0;

            while (cellsInRow.hasNext()) {
                Cell currentCell = cellsInRow.next();
                if (cellIndex == 0) { // item name
                    itemDetail.setItemName(currentCell.getStringCellValue());

                }else if(cellIndex==1) {
                    itemDetail.setItemDescription(currentCell.getStringCellValue());
                } else if (cellIndex == 2) { // item description
                    itemDetail.setItemCode(currentCell.getStringCellValue());
                } else if (cellIndex == 3) { // item unit price and uploadView date time
                    itemDetail.setItemUnitPrice(currentCell.getNumericCellValue());

                }else if(cellIndex==4){ //Meausement UNIT
                    itemDetail.setMeasurementUnit(currentCell.getStringCellValue());
                }

                cellIndex++;


            }

            itemDetail.setDate(DateUtility.todaysDate());
            itemDetail.setRowNumber(rowNumber);
            itemDetails.add(itemDetail);
            rowNumber++;
        }

        // Close WorkBook
        workbook.close();

        return itemDetails;
    }
}
