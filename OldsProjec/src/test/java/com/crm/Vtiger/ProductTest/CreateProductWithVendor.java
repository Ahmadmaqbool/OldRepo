package com.crm.Vtiger.ProductTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.comcast.GenericUtilities.ExcelUtility;
import com.crm.comcast.GenericUtilities.FileUtility;
import com.crm.comcast.GenericUtilities.IConstants;
import com.crm.comcast.GenericUtilities.JavaUtility;
import com.crm.comcast.GenericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductWithVendor {
   public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException, InterruptedException  {
	   
	   WebDriver driver=null;
	   
        JavaUtility jLib=new JavaUtility();
        WebDriverUtility wLib=new WebDriverUtility();
        ExcelUtility eLib=new ExcelUtility();
        FileUtility fLib=new FileUtility();
        
        //fetch the data from property file......
        String BROWSER = fLib.getPropertyValue("browser");
        String URL = fLib.getPropertyValue("url");
        String USERNAME = fLib.getPropertyValue("username");
        String PASSWORD = fLib.getPropertyValue("password");
        
       
		if(BROWSER.equalsIgnoreCase("chrome"))
        {
        	System.setProperty(IConstants.chromeKey, IConstants.chromeValue);
        	driver=new ChromeDriver();
        }
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		
		//fetch the data from excel.......
		
        //using random 
		int randomNumber = jLib.getRandomNum();
		//fetch the data from excel.......
		String vname = eLib.getDataFromExcel("Organization", 1, 2)+randomNumber;
		String prName = eLib.getDataFromExcel("Organization", 2, 6)+randomNumber;
		//maximizing the window............
		wLib.maximizeTheBrowser(driver);
		//giving implicitly wait...........
		wLib.waitTillPageGetsLoad(driver);
		//URL of VtigerCRM.................
		driver.get(URL);
		//USERNAME of VtigerCRM
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		//PASSWORD of VtigerCRM...........
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on login button..........
		driver.findElement(By.id("submitButton")).click();			
		Thread.sleep(3000);
		//moving the mouse pointer to the more..........
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		//using actions class..........
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
		//click on vendor link..........
		driver.findElement(By.xpath("//a[@name='Vendors']")).click();
		//click on vendor button............
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//write the vendor name...........
		driver.findElement(By.xpath("//input[@name='vendorname']")).sendKeys(vname);
		//click on save button...........
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		//click on product module..........
		driver.findElement(By.xpath("//a[.='Products']")).click();
		//click on product button
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//write the product name..........
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(prName);
		//click on vendor name button...........
     	driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
     	//vendor name page window should be open.........
        wLib.switchToWindow(driver, "Vendors&action");
     		
     	Thread.sleep(2000);
     	//search for the vendor name.........
     	driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(vname);
     	//click on search button........
        driver.findElement(By.xpath("//input[@name='search']")).click();
        //click on particular vendor..........
        driver.findElement(By.xpath("//a[text()='"+vname+"']")).click();
        //for close the vendor name window..........
        wLib.switchToWindow(driver, "Products&action");
        //click on save button.....
     	driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();	
     	//verify product is created or not.......
     	String product = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
        if(product.contains(prName))
     	 {
     		 System.out.println("product is created");
     	 }
     	else
     	 {
     		 System.out.println("product is not created");
     	 }
        //mouse hover
     	 WebElement elements = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
         wLib.mouseHoverOnElement(driver, elements);
     	 //click on sign out button...............
     	 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
     		
}
}
