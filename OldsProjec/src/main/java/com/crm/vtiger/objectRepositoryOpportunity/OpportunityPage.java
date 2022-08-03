package com.crm.vtiger.objectRepositoryOpportunity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunityPage {
     
	//initialization.......
	public OpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration............
	@FindBy(xpath= "//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement opportunityLkpImg;
	
	//utilization...........
	public WebElement getOpportunityLkpImg() {
		return opportunityLkpImg;
	}
}
