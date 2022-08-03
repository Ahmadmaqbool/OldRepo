package seleniumProgram;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CareInsuranceCAlenderPOPUp {
	 static {
	    	System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
	    }
	    public static void main(String[] args) throws InterruptedException {
			WebDriver driver=new ChromeDriver();
			driver.get("https://www.careinsurance.com/rhicl/proposalcp/renew/index-care");
			Thread.sleep(2000);
			driver.findElement(By.id("policynumber")).sendKeys("123");
			driver.findElement(By.id("dob")).click();
			Thread.sleep(2000);
			WebElement m=driver.findElement(By.xpath("//select[@class='ui-datepicker-month']"));
			Select s=new Select(m);
			s.selectByValue("1");
			WebElement y=driver.findElement(By.xpath("//select[@class='ui-datepicker-year']"));
			Select s1=new Select(y);
			s1.selectByValue("1999");
			driver.findElement(By.xpath("//a[text()='19']")).click();
			driver.findElement(By.id("alternative_number")).sendKeys("9876544213");
			driver.findElement(By.xpath("//button[text()='Lets Renew']")).click();
			String text=driver.findElement(By.id("policynumberError")).getText();
			if(text.equals("Please enter valid Policy No.")) {
				System.out.println("test case pass");
			}
			else {
				System.out.println("test case fail");
			}
			driver.close();
	    }
}

