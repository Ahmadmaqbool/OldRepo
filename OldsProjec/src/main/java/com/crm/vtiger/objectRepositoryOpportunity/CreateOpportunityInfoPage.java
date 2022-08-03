package com.crm.vtiger.objectRepositoryOpportunity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOpportunityInfoPage {
    
	//initialization.......
	public CreateOpportunityInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration..............
	
	@FindBy(xpath= "//span[@class='dvHeaderText']") private WebElement oppName;
	
	//utilization.............
	
	public WebElement getOppName() {
		return oppName;
	}
}
