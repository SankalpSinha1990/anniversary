package com.avik.java.anniversary.util;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateCheckerUtil {
	
	private boolean anniversaryFlag = false;
	
	public boolean anniversaryDateChecker(String strAnniversaryDate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
		String formattedStrAnniversaryDate = java.time.MonthDay.parse(strAnniversaryDate, formatter).toString();
		String formattedStrCurrentDate = getFormattedStrCurrentDate();
		
		if(formattedStrAnniversaryDate.equalsIgnoreCase(formattedStrCurrentDate))
			anniversaryFlag = true;
		
		return anniversaryFlag;
	}
	
	private String getFormattedStrCurrentDate() {
		
		String formattedStrCurrentDate = "";
		Date currentDate = new Date();
		String strDate, strMonth, strToday = "";
		
		if(currentDate.getDate()<10) {
			strDate = "0"+currentDate.getDate();
		} else {
			strDate = new Integer(currentDate.getDate()).toString();
		}
		if(currentDate.getMonth()<9) {
			strMonth = "0"+(currentDate.getMonth()+1);
		} else {
			strMonth = new Integer(currentDate.getMonth()+1).toString();
		}
		strToday = strMonth + "-" + strDate;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
		formattedStrCurrentDate = java.time.MonthDay.parse(strToday, formatter).toString();
		System.out.println("Today: "+formattedStrCurrentDate);
		
		return formattedStrCurrentDate;
	}

}
