package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ToInsertDataIntoRMGYantraAndVerifyInDB {
    public static void main(String[] args) throws Throwable {
    	String project_name="Dream12";
    	 Connection connection = null;
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		
		
		//maximize the browser
		driver.manage().window().maximize();
		//implicitly wait for 10 seconds
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//enter the URL of RMG Yantra 
		driver.get("http://localhost:8084");
	
		//enter the username in username text field
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		//enter the password in password text field
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		//click on sign in button
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		//click on project link
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		//click on create project link
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		//enter the project name
		driver.findElement(By.name("projectName")).sendKeys(project_name);
		//enter the project manager name
		driver.findElement(By.name("createdBy")).sendKeys("Prakash");
		//select the status from project status from drop down
		WebElement xyz =driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		
		Select s=new Select(xyz);
		s.selectByVisibleText("Completed");
		//click on add project button
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
	try {
			//get register for my SQL database
		    
			Driver driverRef=new Driver();
			DriverManager.registerDriver(driverRef);
			//connect to MySQL DB using URL
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			//create a statement
			Statement statement = connection.createStatement();
			//write a query
			String query = "select * from project";
			ResultSet result=statement.executeQuery(query);
			//verify the data in DB
			boolean flag=false;
			while(result.next())
			{
				String allProjects = result.getString(4);
				
				if(allProjects.contains(project_name))
				{
					flag=true;
					break;
				}
			}
			if(flag)
			{
				System.out.println("prroject is created in database");
			}
			else 
			{
				System.out.println("project is not created in database");
			}
	
		}
	catch(Exception e){
		e.printStackTrace();
	}finally {
		connection.close();
	}
	driver.quit();
	}
}
