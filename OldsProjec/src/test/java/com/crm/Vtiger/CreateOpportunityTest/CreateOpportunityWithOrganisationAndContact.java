package com.crm.Vtiger.CreateOpportunityTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.crm.comcast.GenericUtilities.ExcelUtility;
import com.crm.comcast.GenericUtilities.FileUtility;
import com.crm.comcast.GenericUtilities.JavaUtility;
import com.crm.comcast.GenericUtilities.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunityWithOrganisationAndContact {
        public static void main(String[] args) throws IOException {
       	    WebDriver driver=null;
       	    
       	    FileUtility fLib=new FileUtility();
       	    ExcelUtility eLib=new ExcelUtility();
       	    JavaUtility jLib=new JavaUtility();
       	    WebDriverUtility wLib=new WebDriverUtility();
       	    
       	    String BROWSER = fLib.getPropertyValue("browser");
       	    String URL = fLib.getPropertyValue("url");
       	    String USERNAME = fLib.getPropertyValue("username");
       	    String PASSWORD = fLib.getPropertyValue("password");
       	    
       	    if(BROWSER.equalsIgnoreCase("chrome"))
       	    {
       	    	WebDriverManager.chromiumdriver().setup();
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
       	    //random
       	    int randomNum=jLib.getRandomNum();
       	    //fetch the data from excel......
       	    String opportunityName = eLib.getDataFromExcel("Organization", 1, 8)+randomNum;
       	    
			
		    //maximize the window...........
			driver.manage().window().maximize();
			//implicitly wait..............
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			//URL 
			driver.get(URL);
			//USERNAME
			driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
			//PASSWORD
			driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
			//CLick on login button..........
			driver.findElement(By.id("submitButton")).click();
			//click on opportunity module...........
			driver.findElement(By.xpath("//a[text()='Opportunities']")).click();
			//click on opportunity button..........
			driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
			//write the opportunity name.......
			driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys(opportunityName);
			//click on Organization name button.........
			driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
			//switch to child window.....
		    wLib.switchToWindow(driver, "Accounts&action");
		    
			driver.findElement(By.xpath("//a[text()='Jspider92']")).click();
			//switch to main window.......
			wLib.switchToWindow(driver, "Potentials&action");
			//click on campaign name button.......
			driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[2]")).click();
		    //switch to child window.....
			wLib.switchToWindow(driver, "Campaigns&action");
			//click on vendor name.......
			driver.findElement(By.xpath("//a[text()='TyssRmg67']")).click();
			//switch back to the main window
	        wLib.switchToWindow(driver,"Potentials&action");
			//write in the text............
			driver.findElement(By.xpath("//textarea[@class='detailedViewTextBox']")).sendKeys("Thanks");
			//click on save button...........
			driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
			//verify the opportunity is created or not..........
			String oportunity = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(oportunity.contains(opportunityName))
			{
				System.out.println("oportunity is created");
			}
			else
			{
				System.out.println("oportunity is not created");
			}
			//moving the mouse pointer on the particular element using actions class...........
			 WebElement elements = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			 wLib.mouseHoverOnElement(driver, elements);
			 //click on sign out link........
			 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		}
}
