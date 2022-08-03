package com.crm.comcast.GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * @author MAQBOOL
 *
 */
public class FileUtility {
	/**
	 * 
	 * @param key
	 * @return
	 * @throws IOException
	 */
     public String getPropertyValue(String key) throws IOException {
    	 FileInputStream fileInputStream=new FileInputStream(IConstants.filePath);
    	 Properties properties=new Properties();
    	 properties.load(fileInputStream);
    	
		String value=properties.getProperty(key);
		return value;
     }
}
