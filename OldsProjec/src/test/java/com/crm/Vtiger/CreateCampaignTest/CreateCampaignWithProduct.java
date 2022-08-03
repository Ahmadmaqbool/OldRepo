package com.crm.Vtiger.CreateCampaignTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author MAQBOOL
 *
 */
public class CreateCampaignWithProduct {
    public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
   	 //using property file to take data........
   	 FileInputStream fileInputStreamPr=new FileInputStream("./src/test/resources/commondata.properties.txt");
    	Properties properties=new Properties();
    	properties.load(fileInputStreamPr);
    	String URL = properties.getProperty("url");
    	String USERNAME = properties.getProperty("username");
    	String PASSWORD=properties.getProperty("password");
    	//to take the data from excel file.........
    	FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Organisation1.xlsx");
    	Workbook workbook = WorkbookFactory.create(fileInputStream);
    	Sheet sheet = workbook.getSheet("Organization");
    	Row row = sheet.getRow(1);
    	Cell cell = row.getCell(2);
    	String value = cell.toString();
    	//open the browser........
     	WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//using random class to take the no. randomly........
		Random random=new Random();
		int randomNum=random.nextInt(100);
		//storing the String value with randomNumber in campaignName...........
		String campaignName=value+randomNum;
	    //maximizing the window.......
		driver.manage().window().maximize();
		//implicitly wait to find the element.........
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//URL of VtigerCRM......
		driver.get(URL);
		//USERNAME of VtigerCRM
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		//PASSWORD of VtigerCRM....
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		//click on login button......
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(3000);
		//hovering the mouse pointer on more element........ 
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		//Actions class for mouse hovering....
		Actions a=new Actions(driver);
		//to perform the mouse hovering.......
		a.moveToElement(element).perform();
		//click on campaign module........
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		//click on campaign module button..which is present under the Campaign module.......
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		//writing the campaignName.......
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(campaignName);
		//click on product button under the campaign...to select the product name....
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		//open child window.....
		Set<String> cWindow = driver.getWindowHandles();
		for(String cw:cWindow)
		{
			driver.switchTo().window(cw);
			String title = driver.getTitle();
			if(title.contains("Products&action"))
			{
				break;
			}
		}
		//select the product name..............
		driver.findElement(By.xpath("(//a[text()='cv135'])[1]")).click();
		Set<String> mWindow = driver.getWindowHandles();
		for(String mw:mWindow)
		{
		   driver.switchTo().window(mw);
		   String title = driver.getTitle();
		   if(title.contains("Campaigns&action"))
		   {
			   break;
		   }
		}
		//save the campaign.......
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		//verify the campaign is created or not...
		 String campaign = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		 if(campaign.contains(campaignName))
		 {
			 System.out.println("campaign is created");
		 }
		 else 
		 {
			 System.out.println("campaign is not created");
		 }
		
		//mouse hover
		 WebElement elements = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		 Actions actions1=new Actions(driver);
		 actions1.moveToElement(elements).perform();
		 //sign out
		 driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
}
