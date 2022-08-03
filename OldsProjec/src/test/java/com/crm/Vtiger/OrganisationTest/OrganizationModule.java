package com.crm.Vtiger.OrganisationTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
import com.crm.vtiger.objectReopositoryOrganisation.CreateOrganisationPage;
import com.crm.vtiger.objectReopositoryOrganisation.OrganisationInfoPage;
import com.crm.vtiger.objectReopositoryOrganisation.OrganisationPage;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrganizationModule {
     public static void main(String[] args) throws IOException, InterruptedException  {
    	 WebDriver driver=null;
    	 
    	 JavaUtility jLib=new JavaUtility();
    	 ExcelUtility eLib=new ExcelUtility();
    	 FileUtility fLib=new FileUtility();
    	 WebDriverUtility wLib=new WebDriverUtility();
    	 
    	 //fetch the common data from property file......
    	 String BROWSER=fLib.getPropertyValue("browser");
    	 String URL = fLib.getPropertyValue("url");
    	 String USERNAME = fLib.getPropertyValue("username");
    	 String PASSWORD = fLib.getPropertyValue("password");
    	 
    	 if(BROWSER.equalsIgnoreCase("chrome"))
    	 {
    		 System.setProperty(IConstants.chromeKey, IConstants.chromeValue);
    		 //WebDriverManager.chromeDriver().setup();
    		 driver=new ChromeDriver();
    	 }
    	 else if(BROWSER.equalsIgnoreCase("firefox"))
    	 {
    		 WebDriverManager.firefoxdriver().setup();
    		 driver=new FirefoxDriver();
    	 }
    	 else {
    		 driver=new ChromeDriver();
    	 }
    	   
    	    //to get the randomNum.....
    	    int randomNum=jLib.getRandomNum();
    	    
            //fetch data from excel Sheet.........
    	    String orgName=eLib.getDataFromExcel("Organization", 1,2)+randomNum;
    	    String OrgDropDown=eLib.getDataFromExcel("Organization", 6, 13);
    	 
    	    //maximize the window......
    	    wLib.maximizeTheBrowser(driver);
    	    //implicitly wait for 10 second.......
    	    wLib.waitTillPageGetsLoad(driver);
			//URL of VtigerCRM..
			driver.get(URL);
			//write the username, password and click on save button.........
			
			LoginPage loginPage = new LoginPage(driver);
			loginPage.loginToApp(USERNAME, PASSWORD);
			
			//click on organisation link.......
			HomePage homePage=new HomePage(driver);
			homePage.getOrganisationLink().click();
			
			//click on Organisation LkpImg.......
			OrganisationPage organisationPage=new OrganisationPage(driver);
			organisationPage.getOrganisationLkpImg().click();
			
			//write the organisation name and click on save button......
			
			CreateOrganisationPage createOrganisationPage=new CreateOrganisationPage(driver);
			createOrganisationPage.createOrg(orgName);
			createOrganisationPage.getSaveBtn().click();
			//verify it,s created or not...........
			 OrganisationInfoPage organisationInfoPage=new OrganisationInfoPage(driver);
			 String oname = organisationInfoPage.getOrgname().getText();
				if(oname.contains(orgName))
				{
					System.out.println("organization is created");
				}
				else
				{
					System.out.println("organization is not created");
				}
				//mouse hover and signout........
				HomePage homePage1=new HomePage(driver);
				homePage1.Logout(driver);
				
	}
}
