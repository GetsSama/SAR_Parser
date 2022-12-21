package edu.zhuravlev.datahandler;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class ExcelCreator {
    public static void createTable(String pathToXLS, String sheetName, ToTableData data) {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet(sheetName);

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue(data.getHeaders()[0]);
        header.createCell(1).setCellValue(data.getHeaders()[1]);

        int rowCounter = 1;
        for (var pair : data.getData().entrySet()) {
            Row dataRow = sheet.createRow(rowCounter);
            dataRow.createCell(0).setCellValue(pair.getKey().toString().replace('.',','));
            dataRow.createCell(1).setCellValue(pair.getValue().toString().replace('.',','));
            rowCounter++;
        }

        try (OutputStream fileOut = new FileOutputStream(pathToXLS)) {
            wb.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
