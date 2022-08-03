package com.crm.vtiger.objectRepositoryContact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtilities.WebDriverUtility;

public class CreateContactPage extends WebDriverUtility{
    
	//initialization.........
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration.........
	@FindBy(name= "firstname") private WebElement firstNameEdt;
	
	@FindBy(name= "lastname") private WebElement lastNameEdt;
	
	@FindBy(xpath= "//img[@src='themes/softed/images/select.gif' and @onclick='return window.open(\"index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=\",\"test\",\"width=640,height=602,resizable=0,scrollbars=0\");']") private WebElement orgnameImg;
	
	@FindBy(xpath= "//a[text()='Jspider92']") private WebElement selectOrgName;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']") private WebElement saveBtn;
	
	//utilization..........
	public void getFirstNameEdt(String fname) {
		firstNameEdt.sendKeys(fname);
	}
	public void getLastNameEdt(String lname) {
		lastNameEdt.sendKeys(lname);
	}
	public void selectOrgName(WebDriver driver) {
		orgnameImg.click();
		switchToWindow("Accounts&action", driver);
		selectOrgName.click();
		switchToWindow("Contacts&action", driver);
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
}
