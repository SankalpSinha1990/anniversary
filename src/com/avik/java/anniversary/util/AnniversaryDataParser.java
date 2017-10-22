package com.avik.java.anniversary.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.avik.java.anniversary.messaging.MessageSender;
import com.avik.java.anniversary.model.AnniversaryMessage;
import com.avik.java.anniversary.model.Person;

public class AnniversaryDataParser {
	
	public static void main(String[] args) {
		
		try {
			
			FileInputStream inputFile = new FileInputStream(new File("C:\\javalab\\anniversary\\data\\"+"anniversary.xls"));
			
			HSSFWorkbook workbook = new HSSFWorkbook(inputFile);
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
			inputFile.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
