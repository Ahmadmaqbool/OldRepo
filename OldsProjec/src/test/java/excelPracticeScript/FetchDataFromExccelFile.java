package excelPracticeScript;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExccelFile {
      public static void main(String[] args) throws IOException {
		FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Book2.xlsx");
		Workbook workbook=WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("Sheet1");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(1);
//		String value = cell.toString();
//		System.out.println(value);
		DataFormatter format=new DataFormatter();
		String value = format.formatCellValue(cell);
		System.out.println(value);
		
		
		
	}
}
