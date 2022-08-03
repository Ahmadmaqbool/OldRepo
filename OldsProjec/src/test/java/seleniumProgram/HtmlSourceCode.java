package seleniumProgram;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HtmlSourceCode {
     static {
    	 System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
     }
     public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		String htmlSourceCode = driver.getPageSource();
		System.out.println(htmlSourceCode);
		driver.close();
	}
}
