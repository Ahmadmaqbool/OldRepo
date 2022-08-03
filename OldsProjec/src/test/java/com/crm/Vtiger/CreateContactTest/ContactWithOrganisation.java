

package com.crm.Vtiger.CreateContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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
public class ContactWithOrganisation {
    public static void main(String[] args) throws IOException {
    	//using property class to take the data from  property file...
    	FileInputStream fileInputStream1=new FileInputStream("./src/test/resources/commondata.properties.txt");
    	Properties properties=new Properties();
    	properties.load(fileInputStream1);
    	String URL = properties.getProperty("url");
    	String USERNAME = properties.getProperty("username");
    	String PASSWORD=properties.getProperty("password");
    	//open the browser........
        WebDriverManager.chromedriver().setup();
     	WebDriver driver=new ChromeDriver();
     	//URL of VtigerCRM.............
    	driver.get(URL);
    	//USERNAME...........
        driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
        //PASSWORD................
    	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
    	//click on LOGIN button.............
    	driver.findElement(By.id("submitButton")).click();
        //using RANDOM class to give number randomly.........
 		Random random=new Random();
 		int randomNum=random.nextInt(500);
 		//using fileInputstream class to take data from excel file.....
    	FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Organisation1.xlsx");
    	Workbook workbook = WorkbookFactory.create(fileInputStream);
    	Sheet sheet = workbook.getSheet("Organization");
    	Row row = sheet.getRow(1);
    	Cell cell = row.getCell(4);
    	Row row1 = sheet.getRow(1);
    	Cell cell1 = row1.getCell(5);
   
    	
    	
    	String value = cell.toString();
    	String value1 = cell1.toString();

    	//storing the first name in fname variable with random number.........
    	String fname=value+randomNum;
    	//storing the last name in lname variable with random number.........
    	String lname = value1+randomNum;
    
    	
    	
    	//maximizing the window............
 		driver.manage().window().maximize();
 		//giving the implicitly wait.............
 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 		//click on contact module..........
 		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
 		//click on contact button..........
 		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
 		//giving the first name............
 		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(fname);
 		//giving the last name...........
 		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lname);
 		//click on organization name button........
 		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
 		//child window page should be open...............
 		Set<String> cWindow = driver.getWindowHandles();
 		for(String cw:cWindow)
 		{
 			driver.switchTo().window(cw);
 			String title = driver.getTitle();
 			if(title.contains("Accounts&action"))
 			{
 				break;
 			}
 		}
 		//click on organization name...........
 		driver.findElement(By.xpath("//a[text()='Qspider389']")).click();
 		//child window should be closed.............
 		Set<String> mWindow = driver.getWindowHandles();
 		for(String mw:mWindow)
 		{
 			driver.switchTo().window(mw);
 			String title = driver.getTitle();
 			if(title.contains("Contacts&action"))
 			{
 				break;
 			}
 		}
 		//click on save button...............
 		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
 		//verify it,s created or not.......
 		String verify = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
 		if(verify.contains(lname))
 		{
 			System.out.println("contact is created");
 		}
 		else
 		{
 			System.out.println("contact is not created");
 		}
 		//mouse hover for sign out.....
 		WebElement mHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
 		Actions a=new Actions(driver);
 		a.moveToElement(mHover).perform();
 		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
}
