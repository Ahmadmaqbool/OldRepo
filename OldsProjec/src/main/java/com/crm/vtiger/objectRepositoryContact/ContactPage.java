package com.crm.vtiger.objectRepositoryContact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {
       
	//initialization........
	public ContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//declaration........
	@FindBy(xpath= "//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement contactLkpImg;
	
	//utilization......
	public WebElement getContactLkpImg() {
		return contactLkpImg;
	}
}
