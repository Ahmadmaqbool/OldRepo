package excelPracticeScript;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationTest {
     public static void main(String[] args) throws IOException {
    	 WebDriver driver=null;
    	 FileInputStream fis=new FileInputStream("./src/test/resources/commondata.properties.txt");
    	 Properties properties=new Properties();
    	 properties.load(fis);
    	 
    	 //get the property from the property file by using getProperty
    	 String URL=properties.getProperty("url");
    	 String UN=properties.getProperty("username");
    	 String PWD=properties.getProperty("password");
    	 String BROWSER=properties.getProperty("browser");
    	
    	 
    	 
    	 if(BROWSER.contains("chrome"))
    	 {
    		 WebDriverManager.chromedriver().setup();
    		 driver=new ChromeDriver();
    	 }
    	 else if(BROWSER.contains("firefox"))
    	 {
    		 WebDriverManager.firefoxdriver().setup();
    		 driver=new FirefoxDriver();
    	 }
    	 else
    	 {
    		 driver=new ChromeDriver();
    	 }
    	 
    	 driver.manage().window().maximize();
    	 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    	 driver.get(URL);
    	 driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(UN);
    	 driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PWD);
    	 driver.findElement(By.id("submitButton")).click();
    	 
    	 
         Random random=new Random();
         int randomNum=random.nextInt(500);
         
         FileInputStream fileInputStream11=new FileInputStream("./src/test/resources/Organisation1.xlsx");
         Workbook workbook=WorkbookFactory.create(fileInputStream11);
         Sheet sheet = workbook.getSheet("Organization");
         Row row = sheet.getRow(1);  
         Cell cell = row.getCell(1);
         String value = cell.toString();
         String orgName = value+randomNum;
         driver.findElement(By.xpath("//a[text()='Organizations']")).click();
         driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
         driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(orgName);
         driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
         //verify the organization is created or not.......
         String verify = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
         if(verify.contains(orgName))
         {
        	 System.out.println("orgnisation is created");
         }
         else
         {
        	 System.out.println("orgnisation is not created");
         }
         //logout........
         WebElement mHover = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
         Actions a=new Actions(driver);
         a.moveToElement(mHover).perform();
         driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
         
         
    	
    	 
    	 
    	 
    	 
    	 
	}
}
