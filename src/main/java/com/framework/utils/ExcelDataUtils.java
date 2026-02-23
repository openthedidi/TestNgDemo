package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class ExcelDataUtils {

    private ExcelDataUtils() {
    }

    public static Object[][] getTestData(String excelFilePath, String sheetName) {
        try (FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in: " + excelFilePath);
            }

            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            List<String> headers = new ArrayList<>();
            for (int c = 0; c < colCount; c++) {
                headers.add(headerRow.getCell(c).getStringCellValue());
            }

            int rowCount = sheet.getLastRowNum(); // excludes header row (index 0)
            Object[][] result = new Object[rowCount][1];

            for (int r = 1; r <= rowCount; r++) {
                Row row = sheet.getRow(r);
                HashMap<String, String> map = new HashMap<>();
                for (int c = 0; c < colCount; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    map.put(headers.get(c), getCellValueAsString(cell));
                }
                result[r - 1][0] = map;
            }
            return result;

        } catch (IOException e) {
            throw new RuntimeException("Failed to read Excel test data from: " + excelFilePath, e);
        }
    }

    private static String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> {
                double val = cell.getNumericCellValue();
                if (val == Math.floor(val) && !Double.isInfinite(val)) {
                    yield String.valueOf((long) val);
                }
                yield String.valueOf(val);
            }
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case BLANK -> "";
            default -> "";
        };
    }
}
