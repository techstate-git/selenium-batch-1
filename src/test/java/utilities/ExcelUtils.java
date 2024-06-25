package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelUtils {
    public static void create3HeadersTable(String filename,
                                           String header0,
                                           String header1,
                                           String header2) {

        String filepath = "src/test/resources/data/" + filename + ".xlsx";
        //Create a new Workbook
        Workbook workbook = new XSSFWorkbook();
        //Create a sheet
        Sheet sheet = workbook.createSheet("Sheet1");
        //Create a header row
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue(header0);
        headerRow.createCell(1).setCellValue(header1);
        headerRow.createCell(2).setCellValue(header2);

        //Write the output to a file
        try (FileOutputStream fos = new FileOutputStream(filepath)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("---- Table created ----");
    }

    public static void addRow(String fileName, String cell0, String cell1, String cell2) {
        String filepath = "src/test/resources/data/" + fileName + ".xlsx";
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        try (FileInputStream fis = new FileInputStream(filepath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            int lastRowNum = sheet.getLastRowNum(); //0
            Row newRow = sheet.createRow(lastRowNum + 1); //0 + 1

            newRow.createCell(0).setCellValue(cell0);
            newRow.createCell(1).setCellValue(cell1);
            newRow.createCell(2).setCellValue(cell2);

            try (FileOutputStream fos = new FileOutputStream(filepath)) {
                workbook.write(fos);
            }
            System.out.println("---- Table updated at " + timestamp + "----");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}











