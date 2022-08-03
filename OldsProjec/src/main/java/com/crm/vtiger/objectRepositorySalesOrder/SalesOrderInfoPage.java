package com.crm.vtiger.objectRepositorySalesOrder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrderInfoPage {
    
	//initialization.............
	public SalesOrderInfoPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//declaration...........
	@FindBy(xpath= "//span[@class='lvtHeaderText']") private WebElement salesOrderName;
	
	//utilization..........
	public WebElement getSalesOrderName() {
		return salesOrderName;
	}
}
