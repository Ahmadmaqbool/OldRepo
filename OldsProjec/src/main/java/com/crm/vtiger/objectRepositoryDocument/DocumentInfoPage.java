package com.crm.vtiger.objectRepositoryDocument;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentInfoPage {
      
	//initialization...........
	public DocumentInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration..........
	@FindBy(xpath= "//span[@class='dvHeaderText']") private WebElement documentName;
	
	//utilization...........
	
	public WebElement getDocumentName() {
		return documentName;
	}
}
