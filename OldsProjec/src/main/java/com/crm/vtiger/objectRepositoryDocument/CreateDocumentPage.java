package com.crm.vtiger.objectRepositoryDocument;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class CreateDocumentPage {
      
	//initialization........
	public CreateDocumentPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration.........
	@FindBy(name= "notes_title") private WebElement titleName;
	
	@FindBy(id= "filename_I__") private WebElement file;
	
	@FindBy(xpath= "//input[@title='Save [Alt+S]']") private WebElement saveBtn;
	
	//utilization.........
	public void titleNAme(String title) {
		titleName.sendKeys(title);
		
	}
	public void fileName() {
		
		File f=new File("./src/test/resources/Agile_in_detail.pdf");
		String absolutePath=f.getAbsolutePath();
		file.sendKeys(absolutePath);
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
}
