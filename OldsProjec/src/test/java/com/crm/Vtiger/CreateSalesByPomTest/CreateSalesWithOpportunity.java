package com.crm.Vtiger.CreateSalesByPomTest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.crm.comcast.GenericUtilities.ExcelUtility;
import com.crm.comcast.GenericUtilities.FileUtility;
import com.crm.comcast.GenericUtilities.JavaUtility;
import com.crm.comcast.GenericUtilities.WebDriverUtility;
import com.crm.vtiger.objectRepository.HomePage;
import com.crm.vtiger.objectRepository.LoginPage;
import com.crm.vtiger.objectRepositorySalesOrder.CreateSalesOrderPage;
import com.crm.vtiger.objectRepositorySalesOrder.SalesOrderInfoPage;
import com.crm.vtiger.objectRepositorySalesOrder.SalesOrderPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSalesWithOpportunity {
     
	public static void main(String[] args) throws IOException {
		WebDriver driver=null;
		
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
		String sname = eLib.getDataFromExcel("Organization", 2, 1)+randomNum;
		String billAdd = eLib.getDataFromExcel("Organization", 1, 11);
		String shipAdd= eLib.getDataFromExcel("Organization", 1, 12);
		
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
		
		//homepage
		HomePage homePage=new HomePage(driver);
		//mouse over on more link.........
		homePage.moreLnk(driver);
	
		//click on SalesOrder LkpImg.........
		SalesOrderPage salesOrderPage=new SalesOrderPage(driver);
		salesOrderPage.getSalesOrderLkpImg().click();
		//createSales
		CreateSalesOrderPage createSalesOrderPage=new CreateSalesOrderPage(driver);
		createSalesOrderPage.subject(sname);
		createSalesOrderPage.selectOpportunity(driver);
		createSalesOrderPage.billing(billAdd, shipAdd);
		createSalesOrderPage.item(driver);
		createSalesOrderPage.quntity();
		
		createSalesOrderPage.getSaveBtn().click();
		//verify the SalesOrder is created or not........
		SalesOrderInfoPage salesOrderInfoPage=new SalesOrderInfoPage(driver);
		String salesOrderName = salesOrderInfoPage.getSalesOrderName().getText();
		if(salesOrderName.contains(sname))
		{
			System.out.println("sales order is created");
		}
		else {
			System.out.println("sales order is not created");
		}
		//sign out..........
		homePage.Logout(driver);
		
	}
}
