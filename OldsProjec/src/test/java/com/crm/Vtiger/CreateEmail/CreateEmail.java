 package com.crm.Vtiger.CreateEmail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateEmail {
     public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
    	           FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Organisation1.xlsx");
    	           Workbook workbook = WorkbookFactory.create(fileInputStream);
    	           Sheet sheet = workbook.getSheet("Organization");
    	           Row row = sheet.getRow(2);
    	           Cell cell = row.getCell(2);
    	           String value = cell.toString();
    	           Row row1 = sheet.getRow(1);
    	           Cell cell1 = row1.getCell(7);
    	           String value1 = cell1.toString();
    	  	        WebDriverManager.chromedriver().setup();
    				WebDriver driver=new ChromeDriver();
    				Random random=new Random();
    				int randomNum=random.nextInt(100);
    				
    				String subjectR=value+randomNum;
    			    
    				driver.manage().window().maximize();
    				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    				driver.get("http://localhost:8888");
    				driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
    				driver.findElement(By.name("user_password")).sendKeys("admin");
    				driver.findElement(By.id("submitButton")).click();
    				driver.findElement(By.xpath("//a[text()='Email']")).click();
    				driver.findElement(By.xpath("//a[text()='Compose']")).click();
    				Set<String> cWindow= driver.getWindowHandles();
    				for(String cw:cWindow)
    				{
    					driver.switchTo().window(cw);
    					String title = driver.getTitle();
    					if(title.contains("Emails&action"))
    					{
    						break;
    					}
    				}
    				driver.findElement(By.xpath("//input[@class='txtBox'and @name='parent_name']")).sendKeys(value1);
    				driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(subjectR);
    				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
    				Thread.sleep(3000);
    				driver.findElement(By.xpath("(//input[@name='Send'])[1]")).click();
    			  
	}
}
