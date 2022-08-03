package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility{
        //initialization....
	    public HomePage(WebDriver driver)
	     {
	    	 PageFactory.initElements(driver, this);
	     }
	     
	     //declaration..
	     
	     @FindBy(xpath= "//a[text()='Organizations']") private WebElement organisationsLink;
	     
	     @FindBy(xpath= "//a[text()='Contacts']") private WebElement contactsLink;
	     
	     @FindBy(xpath= "//a[text()='Documents']") private WebElement documentLink;
	     
	     @FindBy(xpath= "//a[text()='Opportunities']") private WebElement opportunityLink;
	     
	     @FindBy(xpath= "//img[@src='themes/softed/images/menuDnArrow.gif']") private WebElement moreLink;
	     
	     @FindBy(name= "Sales Order") private WebElement salesOrderLink;
	     
	     @FindBy(xpath= "//img[@src='themes/softed/images/user.PNG']") private WebElement administratorLnk;
	     
	     @FindBy(xpath= "//a[.='Sign Out']") private WebElement signoutLnk;
	     
	     //utilization.......
	     
	     public WebElement getOrganisationLink() {
	    	 return organisationsLink;
	     }
	     public WebElement getContactsLnk() {
	    	 return contactsLink;
	     }
	     public WebElement getDocumentLnk() {
	    	 return documentLink;
	     }
	     public WebElement getOpportunityLnk() {
	    	 return opportunityLink;
	    	 
	    	 
	     }
	     public WebElement getMoreLink() {
	    	 return moreLink;
	     }
	     public WebElement getSalesOrderLnk() {
	    	 return salesOrderLink;
	     }
	     public WebElement getAdministratorLink() {
	    	 return administratorLnk;
	     }
	     public WebElement getSignOutLink() {
	    	 return signoutLnk;	 	 
	     }
	     public void moreLnk(WebDriver driver) {
	    	 mouseHoverOnElement(driver, moreLink);
	    	 salesOrderLink.click();
	    	 
	     }
	     
	     public void Logout(WebDriver driver) {
	    	 mouseHoverOnElement(driver, administratorLnk);
	    	 signoutLnk.click();
	     }
	     
	     
	     
	     
	     
	     
	    	 
	     
}
