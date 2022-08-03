package com.crm.vtiger.objectRepositoryContact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
      
	//initialization...........
	public ContactInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration...........
	@FindBy(xpath= "//span[@class='dvHeaderText']") private WebElement contactName;
	
	//utilization.........
	public WebElement getContactInfo() {
		return contactName;
	}
}
