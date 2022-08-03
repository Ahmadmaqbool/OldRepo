package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ToInsertDataIntoDB {
	public static void main(String[] args) throws SQLException {
		Driver driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		Statement statement = connection.createStatement();
		String query="insert into student values('Sanjay','Babu','6454','1997/05/13')";
		int result=statement.executeUpdate(query);
		if(result==1)
			System.out.println("data is created in  table");
		else
			System.out.println("data is not created");
	
	connection.close();
	}
}
