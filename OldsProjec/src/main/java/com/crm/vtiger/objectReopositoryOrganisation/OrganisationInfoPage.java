package com.crm.vtiger.objectReopositoryOrganisation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganisationInfoPage {
        
	//initialization........
	public OrganisationInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration..........
	
	@FindBy(xpath= "//span[@class='dvHeaderText']") private WebElement orgname;
	
	//utilization........
	
	public WebElement getOrgname() {
		return orgname;
	}
	
}
