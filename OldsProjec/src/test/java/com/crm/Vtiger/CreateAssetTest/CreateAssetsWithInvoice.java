package com.crm.Vtiger.CreateAssetTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CreateAssetsWithInvoice {
     public static void main(String[] args) throws 

     IOException {
       	 //using property file to take data........
       	 FileInputStream fileInputStreamPr=new FileInputStream("./src/test/resources/commondata.properties.txt");
        	Properties properties=new Properties();
        	properties.load(fileInputStreamPr);
        	String URL = properties.getProperty("url");
        	String USERNAME = properties.getProperty("username");
        	String PASSWORD=properties.getProperty("password");
        	
        	//to take the data from excel file.........
        	FileInputStream fileInputStream=new FileInputStream("./src/test/resources/Organisation1.xlsx");
        	Workbook workbook = WorkbookFactory.create(fileInputStream);
        	Sheet sheet = workbook.getSheet("Organization");
        	Row row = sheet.getRow(1);
        	Cell cell = row.getCell(10);
        	String value = cell.toString();
	}
}
