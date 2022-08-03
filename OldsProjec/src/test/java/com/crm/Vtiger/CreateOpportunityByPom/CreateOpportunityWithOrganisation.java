package com.crm.Vtiger.CreateOpportunityByPom;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.comcast.GenericUtilities.ExcelUtility;
import com.crm.comcast.GenericUtilities.FileUtility;
import com.crm.comcast.GenericUtilities.JavaUtility;
import com.crm.comcast.GenericUtilities.WebDriverUtility;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;
import com.crm.vtiger.objectRepositoryOpportunity.CreateOpportunityInfoPage;
import com.crm.vtiger.objectRepositoryOpportunity.CreateOpportunityPage;
import com.crm.vtiger.objectRepositoryOpportunity.OpportunityPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunityWithOrganisation {
      
	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		
		ExcelUtility eLib=new ExcelUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		
		//fetch data from property file........
		String URL=fLib.getPropertyValue("url");
		String USERNAME=fLib.getPropertyValue("username");
		String PASSWORD=fLib.getPropertyValue("password");
		
		//randomNumber.........
		int randomNum=jLib.getRandomNum();
		//fetch data from excel file............
		String OpName = eLib.getDataFromExcel("Organization", 2, 8)+randomNum;
		
		//browser......
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		//url
		driver.get(URL);
		//maximize the browser...
		wLib.maximizeTheBrowser(driver);
		//implicit wait............
		wLib.waitTillPageGetsLoad(driver);
		//Login......
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);
		//home Page.............
		HomePage homePage=new HomePage(driver);
		homePage.getOpportunityLnk().click();
		//OpprtunityPge..........
		OpportunityPage opportunityPage=new OpportunityPage(driver);
		opportunityPage.getOpportunityLkpImg().click();
		//Create Opportunity.........
		CreateOpportunityPage createOpportunityPage=new CreateOpportunityPage(driver);
		createOpportunityPage.opportunityName(OpName);
		createOpportunityPage.organisationLkpImg(driver);
		createOpportunityPage.getSaveBtn().click();
		//verify Opportunity is created or not............
		CreateOpportunityInfoPage createOpportunityInfoPage=new CreateOpportunityInfoPage(driver);
		String oppName = createOpportunityInfoPage.getOppName().getText();
		if(oppName.contains(OpName)) {
			System.out.println("opportunity is created");
		}
		else {
			System.out.println("opportunity is not created");

		}
		//sign out........
		homePage.Logout(driver);
		
	}
}
