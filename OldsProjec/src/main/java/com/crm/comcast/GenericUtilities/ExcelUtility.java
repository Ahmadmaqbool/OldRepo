package com.crm.comcast.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * 
 * @author MAQBOOL
 *
 */
//gjfhdfd
public class ExcelUtility {
	/**
	 * 
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
       public String getDataFromExcel(String sheetName,int rowNum,int cellNum) throws EncryptedDocumentException, FileNotFoundException, IOException {
       Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
       Sheet sheet = workbook.getSheet(sheetName);
       Row row = sheet.getRow(rowNum);
       Cell cell = row.getCell(cellNum);
       DataFormatter format=new DataFormatter();
       String value = format.formatCellValue(cell);
       return value;   
       }
       
       /**
        * 
        * @param sheetName
        * @param rowNum
        * @param cellNum
        * @param data
        * @throws EncryptedDocumentException
        * @throws FileNotFoundException
        * @throws IOException
        */
      public void insertIntoExcel(String sheetName, int rowNum, int cellNum,String data) throws EncryptedDocumentException, FileNotFoundException, IOException {
    	   Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
    	  Sheet sheet = workbook.getSheet(sheetName);
    	  Row row = sheet.getRow(rowNum);
    	  Cell cell = row.createCell(cellNum);
    	  cell.setCellValue(data);
    	  FileOutputStream fileOutputStream=new FileOutputStream(IConstants.excelPath);
    	  workbook.write(fileOutputStream);
    	  }
      /**
       * 
       * @param sheetName
       * @param rowNum
       * @return
       * @throws EncryptedDocumentException
       * @throws FileNotFoundException
       * @throws IOException
       */
      public int getLastRowNumFromExcel(String sheetName) throws EncryptedDocumentException, FileNotFoundException, IOException {
    	  Workbook workbook = WorkbookFactory.create(new FileInputStream(IConstants.excelPath));
    	  Sheet sheet = workbook.getSheet(sheetName);
    	   int row = sheet.getLastRowNum();
    	   return row;
    	  
      }
        
}
