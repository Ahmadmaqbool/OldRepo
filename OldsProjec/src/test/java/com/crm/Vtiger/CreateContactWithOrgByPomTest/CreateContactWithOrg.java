package com.crm.Vtiger.CreateContactWithOrgByPomTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.comcast.GenericUtilities.ExcelUtility;
import com.crm.comcast.GenericUtilities.FileUtility;
import com.crm.comcast.GenericUtilities.JavaUtility;
import com.crm.comcast.GenericUtilities.WebDriverUtility;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;
import com.crm.vtiger.objectRepositoryContact.ContactInfoPage;
import com.crm.vtiger.objectRepositoryContact.ContactPage;
import com.crm.vtiger.objectRepositoryContact.CreateContactPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrg {
    
    public static void main(String[] args) throws IOException {
    	WebDriver driver=null;
	//uytytd
	FileUtility fLib=new FileUtility();
	ExcelUtility eLib=new ExcelUtility();
	JavaUtility jLib=new JavaUtility();
	WebDriverUtility wLib=new WebDriverUtility();
	
	
	String URL = fLib.getPropertyValue("url");
	String USERNAME = fLib.getPropertyValue("username");
	String PASSWORD = fLib.getPropertyValue("password");
	
	//open the browser.......
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	//to get the random number...........
	int randomNum=jLib.getRandomNum();
	
	//fetch the data from excel.......
	String fname = eLib.getDataFromExcel("Organization", 1, 4)+randomNum;
	String lname = eLib.getDataFromExcel("Organization", 1, 5)+randomNum;
	

	//maximize the window........
	wLib.maximizeTheBrowser(driver);
	//implicit wait for 10 seconds.........
	wLib.waitTillPageGetsLoad(driver);
	//url
	driver.get(URL);
	
	//Login the page........
	LoginPage loginPage=new LoginPage(driver);
	loginPage.loginToApp(USERNAME, PASSWORD);
	
	//click on contact link........
	HomePage homePage=new HomePage(driver);
	homePage.getContactsLnk().click();
	//click on contact lookup img.....
	ContactPage contactPage=new ContactPage(driver);
	contactPage.getContactLkpImg().click();
	//first name
	CreateContactPage createContactPage=new CreateContactPage(driver);
	createContactPage.getFirstNameEdt(fname);
	createContactPage.getLastNameEdt(lname);
	createContactPage.selectOrgName(driver);
	createContactPage.getSaveBtn().click();
	
	//verify the contact is created or not.....
	ContactInfoPage contactInfoPage=new ContactInfoPage(driver);
	String Cname = contactInfoPage.getContactInfo().getText();
	if(Cname.contains(fname))
	{
		System.out.println("contact is created");
	}
	else {
		System.out.println("contact is not created");
	}
	
	//sign out
	homePage.Logout(driver);

	
	
    }
}
