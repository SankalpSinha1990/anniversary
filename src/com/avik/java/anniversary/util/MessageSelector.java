package com.avik.java.anniversary.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import com.avik.java.anniversary.constants.AnniversaryConstants;

public class MessageSelector extends AbstractSelector {

	private int randInt(int min, int max) {
	
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) +1)+ min;
		return randomNum;
	}
	
	public String getRandomMessage() {
		
		String applicationHome = super.getApplicationHome();
		String message = "";
		String dataFileLocation = "";
		Properties applicationProp = new Properties();
		Properties messageProp = new Properties();
		InputStream inputDataLocation = null;
		InputStream messageInput = null;
		
		try {
			
			inputDataLocation = new FileInputStream(applicationHome+"application.properties");
			applicationProp.load(inputDataLocation);
			
			if(applicationProp.getProperty(AnniversaryConstants.DEPLOYMENT_TYPE).equalsIgnoreCase("local")) {
				dataFileLocation = applicationProp.getProperty(AnniversaryConstants.LOCAL_DATA_DIR);
			} else if(applicationProp.getProperty(AnniversaryConstants.DEPLOYMENT_TYPE).equalsIgnoreCase("server")) {
				dataFileLocation = applicationProp.getProperty(AnniversaryConstants.SERVER_DATA_DIR);
			} else {
				System.out.println("[ERROR] Specify correct deployment type in application.properties");
			}
			
			
			
			messageInput = new FileInputStream(dataFileLocation+"messages.properties");
			messageProp.load(messageInput);
			String msgIndex = new Integer(randInt(1, messageProp.size())).toString();
			message = messageProp.getProperty(msgIndex);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(messageInput!=null&&inputDataLocation!=null) {
				try{
					messageInput.close();
					inputDataLocation.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return message;
	}

}
