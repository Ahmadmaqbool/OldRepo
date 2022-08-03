package com.crm.Vtiger;

import java.time.Duration;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;
//gra
//fgdhhgyf
//hgjfhgf
public class AutomateVTiger {
    public static void main(String[] args) throws InterruptedException {
    	WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		Random random=new Random();
		int randomNum=random.nextInt(1000);
		String Oname="Jspider";
		String orgName = Oname+randomNum;
		String firstName="Maqbool";
		String lastName="Ahmad";
		String fName=firstName+randomNum;
		String lName=lastName+randomNum;
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@class='detailedViewTextBox'])[3]")).sendKeys("9875258745");
		
		driver.findElement(By.xpath("//input[@name='account_name']")).sendKeys("Organization");
//		driver.findElement(By.id("employees")).sendKeys("5000");
//		WebElement dropdown = driver.findElement(By.xpath("//select[@name='industry']"));
//		Select s=new Select(dropdown);
//		s.selectByValue("Apparel");
//		WebElement dropdownType = driver.findElement(By.xpath("//select[@name='accounttype']"));
//		Select s1=new Select(dropdownType);
//		s.selectByValue("Competitor");
//		driver.findElement(By.xpath(" (//input[@name='assigntype'])[2]")).click();
//	    driver.findElement(By.xpath("//select[@name='assigned_group_id']")).click();
//		driver.findElement(By.xpath("//input[@name='bill_city']")).sendKeys("bangalore");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		Thread.sleep(3000);
		//verify whether the organization is created or not..
		 String orgname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgname.contains(orgName))
		{
			System.out.println("organization is created");
		}
		else
		{
			System.out.println("organization is not created");
		}
		
		
	//click on contact link.......
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(fName);
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lName);
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		Set<String> childWindow = driver.getWindowHandles();
		for(String ch:childWindow)
		{
			driver.switchTo().window(ch);
			String title = driver.getTitle();
			if(title.contains("Accounts&action"))
			{
				break;
			}
			
		}
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(orgName);
		
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.xpath("//a[.='"+orgName+"']")).click();
		
		
		Set<String> mainWindow = driver.getWindowHandles();
		for(String mn:mainWindow) {
			driver.switchTo().window(mn);
			String title = driver.getTitle();
			if(title.contains("Contacts&action"))
			{
				break;
			}
			
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//verify whether the contact is craeted or not.........
		String contact=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contact.contains(lName)) {
			System.out.println("contact is created");
		}
		else
		{
			System.out.println("contact is not created");
		}
		//mouse hover on administrator link.....
		WebElement element = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions actions=new Actions(driver);
		actions.moveToElement(element).perform();
		//signout.
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		
	}
}
