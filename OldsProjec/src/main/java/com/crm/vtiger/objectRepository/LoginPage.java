package com.crm.vtiger.objectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    //initialization....
	public LoginPage (WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	//declaration......
	@FindBy(name="user_name") private WebElement usernameEdt;
	
	@FindBy(name="user_password") private WebElement passwordEdt;
	
	@FindBy(id="submitButton") private WebElement submitBtn;
	
	//utilization (create business libraries).
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	public void loginToApp(String username, String password)
	{
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		submitBtn.click();
	}
	
	
	
}
