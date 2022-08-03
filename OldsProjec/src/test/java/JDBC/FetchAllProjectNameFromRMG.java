package JDBC;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FetchAllProjectNameFromRMG {
   public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		//maximize the browser
		driver.manage().window().maximize();
		//implicitly wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//enter the url of RMG yantra 
		driver.get("http://localhost:8084");
		Thread.sleep(2000);
		//enter the username in username textfield
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		//enter the passworrd in password textfield
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		//click on sign in button
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		//click on project link
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		
		
		
		
		
		
		
}
}
