package utils;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtils {

    private static final String FILE_PATH = "src/main/resources/TestData.xlsx";
    private Sheet sheet;

    public ExcelUtils(String sheetName) {
        try {
            FileInputStream fis = new FileInputStream(new File(FILE_PATH));
            Workbook workbook = WorkbookFactory.create(fis);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getValue(int row, int col) {
        Cell cell = sheet.getRow(row).getCell(col);
        return cell != null ? cell.toString() : "";
    }
}
