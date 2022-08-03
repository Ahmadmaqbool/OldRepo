package com.crm.Vtiger.CreateDocumentTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocument {
        public static void main(String[] args) throws AWTException, InterruptedException, EncryptedDocumentException, IOException {
        	//Using PROPERTY file for the soft coding.......
        	FileInputStream fileInputStreamPr=new FileInputStream("./src/test/resources/commondata.properties.txt");
        	Properties properties=new Properties();
        	properties.load(fileInputStreamPr);
        	String URL = properties.getProperty("url");
        	String USERNAME = properties.getProperty("username");
        	String PASSWORD=properties.getProperty("password");
        	//Using Excel File ....
        	FileInputStream fileInputStreamEx=new FileInputStream("./src/test/resources/Organisation1.xlsx");
        	Workbook workbook = WorkbookFactory.create(fileInputStreamEx);
        	Sheet sheet = workbook.getSheet("Organization");
        	Row row = sheet.getRow(2);
        	Cell cell = row.getCell(1);
        	String value = cell.toString();
        	//open the browser.......
        	WebDriverManager.chromedriver().setup();
			WebDriver driver=new ChromeDriver();
		    //maximizing the window........
			driver.manage().window().maximize();
			//Using implicitly wait to find the element.........
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//URL of VtigerCRM....
			driver.get(URL);
			//user name
			driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
			//Password
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			//click on save button......
			driver.findElement(By.id("submitButton")).click();
			//click on document module..........
			driver.findElement(By.xpath("//a[text()='Documents']")).click();
			//click o document button.........
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			//write the title...........
			driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(value);
			//USing ROBOT class to write in the text box.........
			Robot r=new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_TAB);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_TAB);
		    //Writing in the text box...........
			r.keyPress(KeyEvent.VK_H);
			r.keyPress(KeyEvent.VK_I);
			
			Thread.sleep(2000);
			//uploading the file............
			File f=new File("./data/Agile_in_detail.pdf");
			String absolutePath = f.getAbsolutePath();
			driver.findElement(By.xpath("//input[@name='filename']")).sendKeys(absolutePath);
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			//verify the document is created or not........
			String document = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(document.contains(value))
			{
				System.out.println("document is created");
			}
			else
			{
				System.out.println("document is not created");
			}
			//mouse hovering.........
			WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			Actions actions=new Actions(driver);
			actions.moveToElement(element).perform();
			//sign out........
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			
			
			
			
        }
}
