package com.crm.comcast.GenericUtilities;

import java.util.Date;
import java.util.Random;

/**
 * 
 * @author MAQBOOL
 *
 */
public class JavaUtility {
	/**
	 * 
	 * @return
	 */
     public int getRandomNum() {
    	 Random random=new Random();
    	 int randomNum=random.nextInt(1000);
    	 return randomNum;
     }
     /**
      * 
      * @return
      */
     public String getSystemDate() {
    	 Date date = new Date();
    	 String dateTime=date.toString();
    	 return dateTime;
     }
     public String getSystemDateInFormat() {
    	 Date date=new Date();
    	 String dateTime=date.toString();
    	 String dateArr[]=dateTime.split(" ");
    	 
    	 int month=date.getMonth()+1;
    	 String date1=dateArr[2];
    	 String year=dateArr[5];
    	 int day=date.getDay();
    	 String time=dateArr[3];
    	 
    	 String finalFormat=month+" "+date+" "+year+" "+day+" "+time;
    	 return finalFormat;
     }
     
}
