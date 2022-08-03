package excelPracticeScript;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class InsertDataToExcelFile {
      public static void main(String[] args) throws  IOException {
    		FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Book2.xlsx");
    		Workbook workbook=WorkbookFactory.create(fileInputStream);
    		Sheet sheet = workbook.getSheet("Sheet1");
    		Row row = sheet.createRow(3);
    		Cell cell = row.createCell(2);
    		
    		cell.setCellValue("hello");
    	    FileOutputStream fileOutputStream=new FileOutputStream("./src/test/resources/Book2.xlsx");
    	    workbook.write(fileOutputStream);
    	    workbook.close();
	}
}
