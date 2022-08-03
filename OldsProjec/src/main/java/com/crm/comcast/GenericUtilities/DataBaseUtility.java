package com.crm.comcast.GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
      static Driver driverRef;
      static Connection connection;
      static ResultSet result;
      //hii
      public void connecttoDB(String DBName) {
    	  try {
    		  driverRef=new Driver();
    		  DriverManager.registerDriver(driverRef);
    		  connection=DriverManager.getConnection(IConstants.Dburl+DBName,IConstants.DBUsername,IConstants.DBPassword);
    	  }
    	  catch(Exception e) {
    		  e.printStackTrace();
    	  }
      }
      public void closeDB() {
    	  try {
    		  connection.close();
    	  }
    	  catch(Exception e)
    	  {
    		  //
    	  }
      }
      public boolean excuteQuery(String query, int ColumnNum, String expectedData) throws SQLException {
    	  result=connection.createStatement().executeQuery(query);
    	  boolean flag=false;
    	  while(result.next())
    	  {
    		  if(result.getString(ColumnNum).equals(expectedData))
    		  {
    			  flag=true;
    			  break;
    		  }
    	  }
    	  if(flag)
    	  {
    		  System.out.println("data is present");
    		  return flag;
    	  }
    	  else {
    		  System.out.println("data is not present");
    		  return flag;
    	  }
      }
      public void excuteUpdate(String query) throws SQLException {
    	  int res=connection.createStatement().executeUpdate(query);
    	  if(res==1)
    	  {
    		  System.out.println("data is updated");
    	  }
    	  else {
    		  System.out.println("data is not updated");
    	  }
    	  
      }
      
}
