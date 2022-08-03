package com.crm.vtiger.objectReopositoryOrganisation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtilities.WebDriverUtility;

public class CreateOrganisationPage extends WebDriverUtility{
    
	//initialization.....
	public CreateOrganisationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//declaration.......
	@FindBy(name= "accountname") private WebElement orgnameEdt;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']") private WebElement saveBtn;
	
	@FindBy(name= "industry") private WebElement industryDrpDwn;
	
	@FindBy(name= "accounttype") private WebElement typeDrpDwn;
	
	//utilization.........
	public WebElement getOrgnameEdt() {
		return orgnameEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
    public void createOrg(String orgName) {
    	orgnameEdt.sendKeys(orgName);
    }
    public void selectIndustry(String industry) {
    	select(industryDrpDwn, industry);
    }
    public void selectType(String type) {
    	select(typeDrpDwn, type);
    }
    
}


