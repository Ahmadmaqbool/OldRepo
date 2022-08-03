package com.crm.Vtiger;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OrganisationWithTYPEAndIndustry {
     public static void main(String[] args) {
     	WebDriverManager.chromedriver().setup();
 		WebDriver driver=new ChromeDriver();
 		driver.manage().window().maximize();
 		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 		driver.get("http://localhost:8888");
 		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
 		driver.findElement(By.name("user_password")).sendKeys("admin");
 		driver.findElement(By.id("submitButton")).click();
 		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
 		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
 		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys("Jspiders");
 		WebElement element = driver.findElement(By.xpath("//select[@name='industry']"));
 		Select s=new Select(element);
 		
 		s.selectByValue("Communications");
 		
 		WebElement element1 = driver.findElement(By.xpath("//select[@name='accounttype']"));
 		Select s1=new Select(element1);
 		s1.selectByValue("Investor");
 		
         driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
         
         //verify it,s created orr not....
		 String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(orgname.contains("Jspiders"))
			{
				System.out.println("organization is created");
			}
			else
			{
				System.out.println("organization is not created");
			}
     	//mouse hover on administrator link.....
 		WebElement elements = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
 		Actions a=new Actions(driver);
 		a.moveToElement(elements).perform();
 		//signout.
 		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
 		
	}
}
