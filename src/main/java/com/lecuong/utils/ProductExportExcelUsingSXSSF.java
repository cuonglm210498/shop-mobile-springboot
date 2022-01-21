package com.lecuong.utils;

import com.lecuong.exception.StatusTemplate;
import com.lecuong.exception.error.BusinessException;
import com.lecuong.modal.response.product.ProductResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class ProductExportExcelUsingSXSSF {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_COLOR = 2;
    public static final int COLUMN_INDEX_BATTERY_CAPACITY = 3;
    public static final int COLUMN_INDEX_OPERATION_SYSTEM = 4;
    public static final int COLUMN_INDEX_SCREEN_SIZE = 5;
    public static final int COLUMN_INDEX_RAM = 6;
    public static final int COLUMN_INDEX_WARRANTY = 7;
    public static final int COLUMN_INDEX_CPU = 8;
    public static final int COLUMN_INDEX_MEMORY = 9;
    public static final int COLUMN_INDEX_THUMBNAIL = 10;
    public static final int COLUMN_INDEX_REAL_CAMERA = 11;
    public static final int COLUMN_INDEX_FRONT_CAMERA = 12;
    public static final int COLUMN_INDEX_STATUS = 13;
    public static final int COLUMN_INDEX_QUANTITY = 14;
    public static final int COLUMN_INDEX_PRICE = 15;
    public static final int COLUMN_INDEX_PROVIDER_NAME = 16;
    public static final int COLUMN_INDEX_CATEGORY_NAME = 17;
    private static CellStyle cellStyleFormatNumber = null;

    public static void writeExcel(List<ProductResponse> productResponses, String excelFilePath) {
        // Create Workbook
        SXSSFWorkbook workbook = new SXSSFWorkbook(-1);

        // Create sheet
        SXSSFSheet sheet = workbook.createSheet("products"); // Create sheet with sheet name

        // register the columns you wish to track and compute the column width
        sheet.trackAllColumnsForAutoSizing();

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (ProductResponse productResponse : productResponses) {
            // Create row
            SXSSFRow row = sheet.createRow(rowIndex);
            // Write data on row
            writeBook(productResponse, row);
            rowIndex++;
        }

//        // Write footer
//        writeFooter(sheet, rowIndex);

        // Auto resize column witdth
        int numberOfColumn = 5; // sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        try {
            createOutputFile(workbook, excelFilePath);
        } catch (IOException e) {
            throw new BusinessException(StatusTemplate.EXPORT_EXCEL_ERROR);
        }
    }

    // Write header with format
    private static void writeHeader(SXSSFSheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        SXSSFRow row = sheet.createRow(rowIndex);

        // Create cells
        SXSSFCell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Id");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Name");

        cell = row.createCell(COLUMN_INDEX_COLOR);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Color");

        cell = row.createCell(COLUMN_INDEX_BATTERY_CAPACITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Battery Capacity");

        cell = row.createCell(COLUMN_INDEX_OPERATION_SYSTEM);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Operation System");

        cell = row.createCell(COLUMN_INDEX_SCREEN_SIZE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Screen size");

        cell = row.createCell(COLUMN_INDEX_RAM);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("RAM");

        cell = row.createCell(COLUMN_INDEX_WARRANTY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Warranty");

        cell = row.createCell(COLUMN_INDEX_CPU);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Cpu");

        cell = row.createCell(COLUMN_INDEX_MEMORY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Memory");

        cell = row.createCell(COLUMN_INDEX_THUMBNAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Thumbnail");

        cell = row.createCell(COLUMN_INDEX_REAL_CAMERA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Real Camera");

        cell = row.createCell(COLUMN_INDEX_FRONT_CAMERA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Front Camera");

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Status");

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Quantity");

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Price");

        cell = row.createCell(COLUMN_INDEX_PROVIDER_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Provider Name");

        cell = row.createCell(COLUMN_INDEX_CATEGORY_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Category Name");
    }

    // Write data
    private static void writeBook(ProductResponse productResponse, SXSSFRow row) {
        if (cellStyleFormatNumber == null) {
            // Format number
            short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
            // DataFormat df = workbook.createDataFormat();
            // short format = df.getFormat("#,##0");

            // Create CellStyle
            SXSSFWorkbook workbook = row.getSheet().getWorkbook();
            cellStyleFormatNumber = workbook.createCellStyle();
            cellStyleFormatNumber.setDataFormat(format);
        }

        SXSSFCell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(productResponse.getId());

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(productResponse.getCategoryName());

        cell = row.createCell(COLUMN_INDEX_COLOR);
        cell.setCellValue(productResponse.getColor());

        cell = row.createCell(COLUMN_INDEX_BATTERY_CAPACITY);
        cell.setCellValue(productResponse.getBatteryCapacity());

        cell = row.createCell(COLUMN_INDEX_OPERATION_SYSTEM);
        cell.setCellValue(productResponse.getOperationSystem());

        cell = row.createCell(COLUMN_INDEX_SCREEN_SIZE);
        cell.setCellValue(productResponse.getScreenSize());

        cell = row.createCell(COLUMN_INDEX_RAM);
        cell.setCellValue(productResponse.getRam());

        cell = row.createCell(COLUMN_INDEX_WARRANTY);
        cell.setCellValue(productResponse.getWarranty());

        cell = row.createCell(COLUMN_INDEX_CPU);
        cell.setCellValue(productResponse.getCpu());

        cell = row.createCell(COLUMN_INDEX_MEMORY);
        cell.setCellValue(productResponse.getMemory());

        cell = row.createCell(COLUMN_INDEX_THUMBNAIL);
        cell.setCellValue(productResponse.getThumbnail());

        cell = row.createCell(COLUMN_INDEX_REAL_CAMERA);
        cell.setCellValue(productResponse.getRealCamera());

        cell = row.createCell(COLUMN_INDEX_FRONT_CAMERA);
        cell.setCellValue(productResponse.getFrontCamera());

        cell = row.createCell(COLUMN_INDEX_STATUS);
        cell.setCellValue(productResponse.getStatus());

        cell = row.createCell(COLUMN_INDEX_QUANTITY);
        cell.setCellValue(productResponse.getQuantity());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_PRICE);
        cell.setCellValue(productResponse.getPrice());
        cell.setCellStyle(cellStyleFormatNumber);

        cell = row.createCell(COLUMN_INDEX_PROVIDER_NAME);
        cell.setCellValue(productResponse.getProviderName());

        cell = row.createCell(COLUMN_INDEX_CATEGORY_NAME);
        cell.setCellValue(productResponse.getCategoryName());

//        // Create cell formula
//        // totalMoney = price * quantity
//        cell = row.createCell(COLUMN_INDEX_TOTAL, CellType.FORMULA);
//        cell.setCellStyle(cellStyleFormatNumber);
//        int currentRow = row.getRowNum() + 1;
//        String columnPrice = CellReference.convertNumToColString(COLUMN_INDEX_PRICE);
//        String columnQuantity = CellReference.convertNumToColString(COLUMN_INDEX_QUANTITY);
//        cell.setCellFormula(columnPrice + currentRow + "*" + columnQuantity + currentRow);
    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.WHITE.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

//    // Write footer
//    private static void writeFooter(SXSSFSheet sheet, int rowIndex) {
//        // Create row
//        SXSSFRow row = sheet.createRow(rowIndex);
//        SXSSFCell cell = row.createCell(COLUMN_INDEX_CATEGORY_NAME, CellType.FORMULA);
//        cell.setCellFormula("SUM(E2:E6)");
//    }

    // Auto resize column width
    private static void autosizeColumn(SXSSFSheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(SXSSFWorkbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
