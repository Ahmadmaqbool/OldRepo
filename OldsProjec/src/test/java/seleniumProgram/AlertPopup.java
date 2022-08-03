package seleniumProgram;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertPopup {
   static {
	   System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
   }
   public static void main(String[] args) throws InterruptedException {
	WebDriver driver=new ChromeDriver();
	driver.get("http://demo.automationtesting.in/Alerts.html");
	driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
	Thread.sleep(2000);
	Alert a=driver.switchTo().alert();
	String text=a.getText();
	System.out.println(text);
	a.dismiss();
	driver.close();
	
}
}
