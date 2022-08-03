package com.crm.Vtiger.CreateSalesOrder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSalesOrderWithOpportunity {
         public static void main(String[] args) throws InterruptedException, IOException {
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
            	Cell cell = row.getCell(9);
            	String value = cell.toString();
      	  	    WebDriverManager.chromedriver().setup();
     			WebDriver driver=new ChromeDriver();
     		    
     			driver.manage().window().maximize();
     			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
     			driver.get(URL);
     			driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
     			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
     			driver.findElement(By.id("submitButton")).click();
     			WebElement mHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
     			Actions actions=new Actions(driver);
     			actions.moveToElement(mHover).perform();
     			driver.findElement(By.xpath("//a[text()='Sales Order']")).click();
     			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
     			driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(value);

     			//using select class to handle the list box of status.............
     			WebElement element = driver.findElement(By.xpath("//select[@name='sostatus']"));
     			Select select=new Select(element);
     			select.selectByValue("Approved");
     			//click on opportunity button...........
     			driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif'and@title='Select'and@onclick='selectPotential()']")).click();
     			Set<String> childWindow = driver.getWindowHandles();
     			for(String cw:childWindow)
     			{
     				driver.switchTo().window(cw);
     				String title = driver.getTitle();
     				if(title.contains("Potentials&action"))
     				{
     					break;
     				}
     			}
     			//select opportunity name........
     			driver.findElement(By.xpath("//a[text()='get a job158']")).click();
     			//opportunity name window should be closed......
     			Set<String> mainWindow = driver.getWindowHandles();
     			for(String mw:mainWindow)
     			{
     				driver.switchTo().window(mw);
     				String title = driver.getTitle();
     				if(title.contains("SalesOrder&action"))
     				{
     					break;
     				}
     			}

     			Thread.sleep(3000);
     			WebElement listBox = driver.findElement(By.xpath("//select[@name='invoicestatus']"));
     			Select select1=new Select(listBox);
     			select1.selectByValue("Created");
     			//write billing address....
     			driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys("1st stage,btm layout,560007");
     			//write shipping address..........
     			driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("btm layout,560007");
     			//click on item name........
     			driver.findElement(By.xpath("//img[@title='Products']")).click();
     			Set<String> childWindowIN = driver.getWindowHandles();
     			for(String cWIN:childWindowIN)
     			{
     				driver.switchTo().window(cWIN);
     				String title = driver.getTitle();
     				if(title.contains("Products&action"))
     				{
     					break;
     				}
     			}
     			//select the item name.........
     			driver.findElement(By.xpath("//a[text()='cv135']")).click();
     			Set<String> mWindowIN = driver.getWindowHandles();
     			for(String mwIN:mWindowIN)
     			{
     				driver.switchTo().window(mwIN);
     				String title = driver.getTitle();
     				if(title.contains("SalesOrder&action"))
     				{
     					break;
     				}
     			}
     			driver.findElement(By.id("qty1")).sendKeys("1");
     			//click on save button.....
     			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
     			//verify the sales order is created or not...........
     			String salesOrder = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
     			if(salesOrder.contains(value))
     			{
     				System.out.println("salesOrder is created");
     			}
     			else
     			{
     				System.out.println("salesOrder is not created");
     			}
     			//using actions class for moving the mouse pointer on particular element.......
     			WebElement element1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
     			Actions actions1=new Actions(driver);
     			actions1.moveToElement(element1).perform();
     			//click on sign out...
     			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
     			
     			
     			
		}
}      
