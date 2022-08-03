package com.crm.Vtiger.CreateDocumentByPomTest;

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
import com.crm.vtiger.objectRepositoryDocument.CreateDocumentPage;
import com.crm.vtiger.objectRepositoryDocument.DocumentPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocument {
   
	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		//gf
		ExcelUtility eLib=new ExcelUtility();
		FileUtility fLib=new FileUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
		
		String URL = fLib.getPropertyValue("url");
		String USERNAME = fLib.getPropertyValue("username");
		String PASSWORD = fLib.getPropertyValue("password");
		
		//random Number....
		int randomNum=jLib.getRandomNum();
		
		//fetch the data from excel........
		String title = eLib.getDataFromExcel("Organization", 2, 6)+randomNum;
		
		//open the browser......
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		//maximize the browser.......
		wLib.maximizeTheBrowser(driver);
		//implicit wait........
		wLib.waitTillPageGetsLoad(driver);
		//url..........
		driver.get(URL);
		//login
		LoginPage loginPage=new LoginPage(driver);
		loginPage.loginToApp(USERNAME, PASSWORD);
		//click on document link........
		HomePage homePage=new HomePage(driver);
		homePage.getDocumentLnk().click();
		//click on documentLkpImg......
		DocumentPage documentPage=new DocumentPage(driver);
		documentPage.getDocumentLkpImg().click();
		//write title
		CreateDocumentPage createDocumentPage=new CreateDocumentPage(driver);
		createDocumentPage.titleNAme(title);
		createDocumentPage.fileName();
		createDocumentPage.getSaveBtn().click();
		//verify document is created or not.........
		ContactInfoPage contactInfoPage=new ContactInfoPage(driver);
		String Dname = contactInfoPage.getContactInfo().getText();
		if(Dname.contains(title))
		{
			System.out.println("document is created");
		}
		else {
			System.out.println("document is not created");
		}
		//sign out
		homePage.Logout(driver);
		
		
		
		
		
		
	}
}
