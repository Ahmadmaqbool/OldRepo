package seleniumProgram;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FileUploadPopupNaukri {
    static {
    	System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }
    public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.naukri.com/");
		driver.findElement(By.xpath("//div[text()='Login']")).click();
		driver.findElement(By.xpath("(//input[@type='text'])[5]")).sendKeys("ahmadmaqbool363@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("naukri@maq");
		driver.findElement(By.xpath("//button[text()='Login']")).click();
		driver.findElement(By.xpath("//div[text()='UPDATE PROFILE']")).click();
		File f=new File("./data/resume2022.docx");
		String absolutePath=f.getAbsolutePath();
		
		driver.findElement(By.id("attachCV")).sendKeys(absolutePath);
	}
}
