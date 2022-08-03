package excelPracticeScript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertMultipleDataToExcel {
     public static void main(String[] args) throws EncryptedDocumentException, IOException {
		FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Book2.xlsx");
		Workbook workbook=WorkbookFactory.create(fileInputStream);
		Sheet sheet = workbook.getSheet("Sheet2");
		WebDriver driver=WebDriverManager.chromedriver().create();
		driver.get("http://localhost:8888");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		int count = links.size();
		System.out.println(count);
		for(int i=0; i<count; i++) {
			Row row = sheet.createRow(i);
			Cell cell = row.createCell(0);
			cell.setCellValue(links.get(i).getAttribute("href"));
		}
		FileOutputStream fos=new FileOutputStream("./src/test/resources/Book2.xlsx");
		workbook.write(fos);
		
		
	}
}
