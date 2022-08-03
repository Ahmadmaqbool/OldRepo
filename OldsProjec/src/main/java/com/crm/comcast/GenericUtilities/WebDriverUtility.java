
package com.crm.comcast.GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * 
 * @author MAQBOOL
 *
 */
public class WebDriverUtility {
	/**
	 * 
	 * @param driver
	 */
    public void maximizeTheBrowser(WebDriver driver) {
    	driver.manage().window().maximize();
    }
    /**
     * 
     * @param driver
     */
    public void minimizeTheBrowser(WebDriver driver) {
    	
    	driver.manage().window().minimize();
    }
    /**
     * 
     * @param driver
     */
    public void refreshThePage(WebDriver driver) {
    	driver.navigate().refresh(); 	
    }
    /**
     * 
     * @param driver
     */
    public void backToPreviousPage(WebDriver driver) {
    	driver.navigate().back();
    }
    /**
     * 
     * @param driver
     */
    public void forwardToNextPage(WebDriver driver) {
    	driver.navigate().forward();
    }
    /**
     * 
     * @param driver
     */
    public void waitTillPageGetsLoad(WebDriver driver) {
    	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IConstants.implicitlyWaitDuration));
    }
    /**
     * 
     * @param driver
     * @param element
     */
    public void waitTillElementToClick(WebDriver driver, WebElement element) {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    /**
     * 
     * @param driver
     * @param element
     */
    public void waitTillElementToVisible(WebDriver driver,WebElement element) {
    	WebDriverWait wait=new WebDriverWait(driver ,Duration.ofSeconds(IConstants.explicitWaitDuration));
    	wait.until(ExpectedConditions.visibilityOf(element));
    }
    /**
     * 
     * @param driver
     * @param title
     */
    public void waitPageLoadTitle(WebDriver driver,String title) {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
    	wait.until(ExpectedConditions.titleContains(title));
    }
    /**
     * 
     * @param driver
     * @param URL
     */
    public void waitTillPageLoadUrl(WebDriver driver, String URL) {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
    	wait.until(ExpectedConditions.urlContains(URL));
    }
    /**
     * 
     * @param driver
     */
    public void ignoreNoSuchElementException(WebDriver driver) {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
    	wait.ignoring(NoSuchElementException.class);
    }
    /**
     * 
     * @param driver
     */
    public void waitForAlertMsg(WebDriver driver)
    {
    	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(IConstants.explicitWaitDuration));
    	wait.until(ExpectedConditions.alertIsPresent());
    }
    /**
     * this method is use to switch to frame using index
     * @param driver
     * @param index
     */
    public void switchToFrame(WebDriver driver, int index) {
    	driver.switchTo().frame(index);
    }
    /**
     * this method is use to switch to frame using id
     * @param driver
     */
    public void switchToFrame(WebDriver driver, String id) {
    	driver.switchTo().frame(id);
    }
    /**
     * this method is use to switch to frame using element
     * @param driver
     * @param element
     */
    public void switchToFrame(WebDriver driver,WebElement element) {
    	driver.switchTo().frame(element);
    }
    /**
     * this method is use to switch to main frame
     * @param driver
     */
    public void switchToMainFrame(WebDriver driver) {
    	driver.switchTo().defaultContent();
    }
    /**
     * 
     * @param element
     * @param index
     */
    public void select(WebElement element, int index) {
    	Select select=new Select(element);
    	select.selectByIndex(index);
    }
    /**
     * 
     * @param element
     * @param value
     */
    public void select(WebElement element, String value) {
    	Select select=new Select(element);
    	select.selectByValue(value);
    }
    /**
     * 
     * @param text
     * @param element
     */
    public void select(String text,WebElement element ) {
    	Select select=new Select(element);
    	select.selectByVisibleText(text);
    }
    /**
     * 
     * @param element
     */
    public void getAllTheOptionsFromDropDown(WebElement element) {
    	Select select=new Select(element);
    	List<WebElement> option = select.getOptions();
    	for(WebElement webElement:option) {
    		String text=webElement.getText();
    		System.out.println(text);
    	  }
     }
    /**
     * 
     * @param driver
     * @param element
     */
    public void mouseHoverOnElement(WebDriver driver, WebElement element) {
    	Actions actions=new Actions(driver);
    	actions.moveToElement(element).perform();
    }
    /**
     * 
     * @param driver
     * @param element
     */
    public void rightClick(WebDriver driver, WebElement element) {
    	Actions actions=new Actions(driver);
    	actions.contextClick(element).perform();
    }
    /**
     * 
     * @param driver
     * @param element
     */
    public void doubleClick(WebDriver driver, WebElement element) {
    	Actions actions=new Actions(driver);
    	actions.doubleClick(element).perform();
    }
    /**
     * 
     * @param driver
     */
    public void clickOnEnterKey(WebDriver driver) {
    	Actions actions=new Actions(driver);
    	actions.sendKeys(Keys.ENTER).perform();
    }
     /**
      * 
      * @param driver
      * @param screenShotName
      */
    public void takeScreenShot(WebDriver driver, String screenShotName) {
    	TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
    	File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
    	File dst = new File("./screenShots"+screenShotName+".PNG");
    	try {
    		FileUtils.copyFile(src, dst);
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    /**
     * 
     * 
     * @param driver
     */
    public void waitAndClickUsingCustomWait(WebDriver driver) {
    	FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver);
    	wait.pollingEvery(Duration.ofSeconds(10));
    	wait.ignoring(NoSuchElementException.class);
    	try {
    		wait.wait(10);
    	}
    	catch(InterruptedException e) {
    		e.printStackTrace();
    	}	
    }
    /**
     * 
     * @param duration
     * @param element
     * @param pollingTime
     */
    public void waitAndClick(int duration, WebElement element, long pollingTime) {
    	int count=0;
    	while(count<duration)//20
    	{
    		try {
    			element.click();
    			break;
    		}
    		catch(Exception e) {
    			try {
    				Thread.sleep(pollingTime);
    			}
    			catch(Exception e2)
    			{
    				e2.printStackTrace();
    			}
    			count++;
    		}
    	}
    }
    public void switchToWindow(WebDriver driver, String actualTitle) {
    	Set<String> set = driver.getWindowHandles();
    	for(String string:set) {
    		driver.switchTo().window(string);
    		String title = driver.getTitle();
    		if(title.contains(actualTitle))
    		{
    			break;
    		}
    	}
    }
    /**
     * 
     * @param actualUrl
     * @param driver
     */
    public void switchToWindow(String actualUrl,WebDriver driver) {
    	Set<String> set = driver.getWindowHandles();
    	Iterator<String> it=set.iterator();
    	while(it.hasNext())
    	{
    		String wId=it.next();
    		driver.switchTo().window(wId);
    		String url=driver.getCurrentUrl();
    		if(url.contains(actualUrl))
    		{
    			break;
    		}
    	}
    }
    /**
     * 
     * @param driver
     * @param expectedMsg
     */
    public void switchToAlertPopUpAndAccept(WebDriver driver, String expectedMsg) {
    	Alert alert=driver.switchTo().alert();
    	if(alert.getText().trim().equalsIgnoreCase(expectedMsg.trim())) {
    		System.out.println("alert msg is verified");
    	}
    	else {
    		System.out.println("alert msg is not verified");
    	}
    	alert.accept();
    }
    public void switchToAlertPopUpAndDismiss(String expectedMsg, WebDriver driver)
    {
    	Alert alert = driver.switchTo().alert();
    	if(alert.getText().trim().equalsIgnoreCase(expectedMsg))
    	{
    		System.out.println("alert msg is verified");
    	}
    	else
    	{
    		System.out.println("alert msg is not verified");
    	}
    	alert.dismiss();
    }
    
 }
