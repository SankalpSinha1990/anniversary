package com.avik.java.anniversary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.avik.java.anniversary.constants.AnniversaryConstants;
import com.avik.java.anniversary.messaging.MessageSender;
import com.avik.java.anniversary.model.AnniversaryMessage;
import com.avik.java.anniversary.model.Person;

public class AnniversaryDataParser {
	
	public static void main(String[] args) {
		
		String applicationHome = AbstractSelector.getApplicationHome();
		
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream(applicationHome+"application.properties");
			prop.load(input);
			String dataFileLocation = "";
			if(prop.getProperty(AnniversaryConstants.DEPLOYMENT_TYPE).equalsIgnoreCase("local")) {
				dataFileLocation = prop.getProperty(AnniversaryConstants.LOCAL_DATA_DIR);
			} else if(prop.getProperty(AnniversaryConstants.DEPLOYMENT_TYPE).equalsIgnoreCase("server")) {
				dataFileLocation = prop.getProperty(AnniversaryConstants.SERVER_DATA_DIR);
			} else {
				System.out.println("[ERROR] Specify correct deployment type in application.properties");
			}
			InputStream dataInputFile = new FileInputStream(new File(dataFileLocation+"anniversary.xls"));
			HSSFWorkbook workbook = new HSSFWorkbook(dataInputFile);
			HSSFSheet worksheet = workbook.getSheet("anniversary");
			
			Iterator<Row> rowIterator = worksheet.iterator();
			while(rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Person person = new Person();
				DateCheckerUtil dateChecker = new DateCheckerUtil();
				
				if(row.getRowNum()!=0) {
					Iterator<Cell> cellIterator = row.cellIterator();
					while(cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if(cell.getCellType()==Cell.CELL_TYPE_STRING) {
							
							//System.out.println(cell.getAddress().formatAsString() + " : " + cell.getStringCellValue());
							if(cell.getAddress().formatAsString().contains("A")){
								person.setNickname(cell.getStringCellValue());
								System.out.println("Nickname: "+person.getNickname());
							}
							if(cell.getAddress().formatAsString().contains("B")){
								person.setFullname(cell.getStringCellValue());
								System.out.println("Full Name: "+person.getFullname());
							}
							if(cell.getAddress().formatAsString().contains("C")){
								person.setEmail(cell.getStringCellValue());
								System.out.println("Email: "+person.getEmail());
							}
							if(cell.getAddress().formatAsString().contains("D")){
								person.setAnniversaryDate(cell.getStringCellValue());
								System.out.println("Anniversary: "+person.getAnniversaryDate());
								if(dateChecker.anniversaryDateChecker(person.getAnniversaryDate())) {
									
									//send B'day mail to the person
									AnniversaryMessage message = new AnniversaryMessage();
									message = message.createMessage(person);
									MessageSender sender = new MessageSender();
									sender.sendMessage(message);
								}
							}
							
						}
					}
				}
				System.out.println("------------------------------------------------------------------");
			}
			workbook.close();
			dataInputFile.close();
			input.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
