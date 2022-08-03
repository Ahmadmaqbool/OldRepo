package com.crm.vtiger.objectRepositoryDocument;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DocumentPage {
     
	//initialization........
	public DocumentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration........
	@FindBy(xpath= "//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement documentLkpImg;
	
	//utilization..........
	
	public WebElement getDocumentLkpImg() {
		return documentLkpImg;
	}
}
