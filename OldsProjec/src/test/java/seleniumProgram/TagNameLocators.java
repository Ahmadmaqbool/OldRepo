package seleniumProgram;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TagNameLocators {
    static {
    	System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
    }
    public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.get("file:///C:/Users/MAQBOOL/Desktop/selenium/Demo.html");
		
	}
}
 