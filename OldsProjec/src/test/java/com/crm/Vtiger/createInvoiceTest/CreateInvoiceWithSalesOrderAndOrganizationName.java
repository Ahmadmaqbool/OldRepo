package com.crm.Vtiger.createInvoiceTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoiceWithSalesOrderAndOrganizationName {
             public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException, AWTException {
             	 //using property file to take data........
               	 FileInputStream fileInputStreamPr=new FileInputStream("./src/test/resources/commondata.properties.txt");
                	Properties properties=new Properties();
                	properties.load(fileInputStreamPr);
                	String URL = properties.getProperty("url");
                	String USERNAME = properties.getProperty("username");
                	String PASSWORD=properties.getProperty("password");
                	
                	//to take the data from excel file.........
                	FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Organisation1.xlsx");
                	Workbook workbook = WorkbookFactory.create(fileInputStream);
                	Sheet sheet = workbook.getSheet("Organization");
                	Row row = sheet.getRow(1);
                	Cell cell = row.getCell(10);
                	String value = cell.toString();
                	//for billing address........
                	Row row1 = sheet.getRow(1);
                	Cell cell1 = row1.getCell(11);
                	String valueBilling = cell1.toString();
                	//for shipping address....
                	Row row2 = sheet.getRow(1);
                	Cell cell2 = row2.getCell(12);
                	String valueShipping = cell2.toString();
                	
                	//open the browser..........
                	WebDriverManager.chromedriver().setup();
                	WebDriver driver=new ChromeDriver();
                	//maximize the window..........
                	driver.manage().window().maximize();
                	//implicitly wait to find the element............
                	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                	//URL
                	driver.get(URL);
                	//USERNAME
                	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
                	//PASSWORD
         			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
         			//click on the login button.........
         			driver.findElement(By.id("submitButton")).click();
         			//moving the mouse pointer........
         			WebElement mHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
         			Actions actions=new Actions(driver);
         			actions.moveToElement(mHover).perform();
         			//click on the invoice link........
         			driver.findElement(By.xpath("(//a[text()='Invoice'])[1]")).click();
         			//click on invoice button.........
         			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
         			//write the subject..........
         			driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(value);
      
         			
         			
         			//click on sales Order button........
         			driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif'and @onclick='selectSalesOrder();']")).click();
         			Set<String> childWindow = driver.getWindowHandles();
         			for(String cw:childWindow)
         			{
         				driver.switchTo().window(cw);
         				String title = driver.getTitle();
         				if(title.contains("SalesOrder&action"))
         				{
         					break;
         				}
         			}
         			
         			//select the salesOrder........
         			driver.findElement(By.xpath("//a[text()='Maths']")).click();
         			
         			Set<String> mainWindow = driver.getWindowHandles();
         			for(String mw:mainWindow)
         			{
         				driver.switchTo().window(mw);
         				String title = driver.getTitle();
         				if(title.contains("Invoice&action"))
         				{
         					break;
         				}
         			}
         			
           			
         			//click on organization button......
         			driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif'and @onclick='return window.open(\"index.php?module=Accounts&action=Popup&popuptype=specific_account_address&form=TasksEditView&form_submit=false&fromlink=\",\"test\",\"width=640,height=602,resizable=0,scrollbars=0\");']")).click();
        			Set<String> childWindowOrg = driver.getWindowHandles();
        			for(String cw1:childWindowOrg)
        			{
        				driver.switchTo().window(cw1);
        				String title = driver.getTitle();
        				if(title.contains("Accounts&action"))
        				{
        					break;
        				}
        			}
        			//click on organiation name....
        			driver.findElement(By.xpath("//a[text()='TyssRmg261']")).click();
        			Robot robot=new Robot();
        			robot.keyPress(KeyEvent.VK_TAB);
        			robot.keyPress(KeyEvent.VK_ENTER);
        			robot.keyRelease(KeyEvent.VK_ENTER);
        			
        			
        			Thread.sleep(3000);
        			Set<String> mainWindowOrg = driver.getWindowHandles();
        			for(String mw1:mainWindowOrg)
        			{
        				driver.switchTo().window(mw1);
        				String title = driver.getTitle();
        				if(title.contains("Invoice&action"))
        				{
        					break;
        				}
        			}
        			
        		
        	
         			
         			//write billing address.......
         			
         			driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(valueBilling);
         	
         			//write the shipping address.......
         			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(valueShipping);
         			//click on item name....
         			driver.findElement(By.id("searchIcon1")).click();
         			Set<String> cWindow = driver.getWindowHandles();
         			for(String cW:cWindow)
         			{
         				driver.switchTo().window(cW);
         				String title = driver.getTitle();
         				if(title.contains("Products&action"))
         				{
         					break;
         				}
         				
         			}
         			//select the item name.............
         			driver.findElement(By.id("popup_product_6")).click();
         			Set<String> mWindow = driver.getWindowHandles();
         			for(String mW:mWindow)
         			{
         				driver.switchTo().window(mW);
         				String title = driver.getTitle();
         				if(title.contains("Invoice&action")) {
         					break;
         				}
         				
         			}
                	driver.findElement(By.id("qty1")).sendKeys("1");
                	//click on save button.......
                	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
                	//verify the invoice is created or not
                	String invoice = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
                	if(invoice.contains(value)) {
                		System.out.println("invoice is created");
                	}
                	else {
                		System.out.println("invoice is not created");
                	}
                	WebElement hover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
                	Actions actions2=new Actions(driver);
                	actions2.moveToElement(hover).perform();
                	//sign Out
                	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			}
}
