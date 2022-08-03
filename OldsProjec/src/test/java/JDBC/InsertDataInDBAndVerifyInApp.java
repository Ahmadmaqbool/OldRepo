package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InsertDataInDBAndVerifyInApp {
           public static void main(String[] args) throws Throwable {
			String project_name="Flinko";
			Connection connection = null;
			WebDriver driver=null;
			try {
			  Driver driverRef=new Driver();
			  DriverManager.registerDriver(driverRef);
			  connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			  Statement statement = connection.createStatement();
			  String query = "insert into project values('TY_PROJ_258','sanjaybabu','13/07/2022','"+project_name+"','Completed','0')";
			  statement.executeUpdate(query);
			  
			  
			  WebDriverManager.chromedriver().setup();
			  driver=new ChromeDriver();
			  driver.get("http://localhost:8084");
			  driver.manage().window().maximize();
			  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
				//enter the password in password text field
				driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
				//click on sign in button
				driver.findElement(By.xpath("//button[text()='Sign in']")).click(); 
				//click on project link
				driver.findElement(By.xpath("//a[text()='Projects']")).click();
				 List<WebElement> projects = driver.findElements(By.xpath("//table[@class='table table-striped table-hover']//tbody/tr/td[2]"));
				 
				 boolean flag=false;
				for(WebElement pr:projects)
				{
					  String allprojects=pr.getText();
					  if(allprojects.contains(project_name))
					  {
						  flag=true;
						  break;
					  }
				}
				if(flag) {
					System.out.println("project is created");
				}
				else
				{
					System.out.println("project is not created");
				}
			}
			catch(Exception e){
				connection.close();
				System.out.println("database is closed");
			}
			connection.close();
			driver.quit();
		}
}
