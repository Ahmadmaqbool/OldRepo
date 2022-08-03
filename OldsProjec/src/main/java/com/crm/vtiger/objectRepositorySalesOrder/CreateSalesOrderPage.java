package com.crm.vtiger.objectRepositorySalesOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtilities.WebDriverUtility;

public class CreateSalesOrderPage extends WebDriverUtility{
     
	//initialization.........
	public CreateSalesOrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration......
	@FindBy(name= "subject") private WebElement subjectName;
	
	@FindBy(xpath= "//img[@src='themes/softed/images/select.gif' and @onclick='selectPotential()']") private WebElement opportunityLkpImg;
	
	@FindBy(xpath= "//a[text()='get a job83']") private WebElement selectOppurtunityName;
	
	@FindBy(name= "bill_street") private WebElement billAddress;
	
	@FindBy(name= "ship_street") private WebElement shipAddress;
	
	@FindBy(id= "searchIcon1") private WebElement itemName;
	
	@FindBy(xpath= "//a[text()='cv135']") private WebElement selectItemName;
	
	@FindBy(name= "qty1") private WebElement quantity;
	
	@FindBy(xpath= "(//input[@title='Save [Alt+S]'])[2]") private WebElement saveBtn;
	
	//utilization..........
	public void subject(String sname) {
		subjectName.sendKeys(sname);
	}
	public void selectOpportunity(WebDriver driver) {
		opportunityLkpImg.click();
		switchToWindow("Potentials&action", driver);
		selectOppurtunityName.click();
		switchToWindow("SalesOrder&action", driver);
		
	}
	public void billing(String billAdd, String shipAdd) {
		billAddress.sendKeys(billAdd);
		shipAddress.sendKeys(shipAdd);
	}
	public void item(WebDriver driver) {
		itemName.click();
		switchToWindow("Products&action", driver);
		selectItemName.click();
		switchToWindow("SalesOrder&action", driver);
	}
	public void quntity() {
		quantity.sendKeys("1");
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
}
