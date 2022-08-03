package com.crm.vtiger.objectRepositoryOpportunity;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtilities.WebDriverUtility;

public class CreateOpportunityPage extends WebDriverUtility{
    
	//initialization...........
	public CreateOpportunityPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration........
	@FindBy(name= "potentialname") private WebElement oppName;
	
	@FindBy(xpath= "(//img[@src='themes/softed/images/select.gif'])[1]") private WebElement orgLkpImg;
	
	@FindBy(xpath= "//a[text()='Pyspider2']") private WebElement selectOrgName;
	
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']") private WebElement saveBtn;
	
	//utilization..........
	public void opportunityName(String OpName) {
		oppName.sendKeys(OpName);
	}
	public void organisationLkpImg(WebDriver driver) {
		orgLkpImg.click();
		switchToWindow("Accounts&action", driver);
		selectOrgName.click();
		switchToWindow("Potentials&action", driver);
		
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	
}