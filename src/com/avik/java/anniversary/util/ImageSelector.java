package com.avik.java.anniversary.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;

import com.avik.java.anniversary.constants.AnniversaryConstants;

public class ImageSelector extends AbstractSelector {
	
	public String getRandomImageFileName() {
		
		String applicationHome = super.getApplicationHome();
		String imageFileLocation = "";
		Properties applicationProp = new Properties();
		InputStream input = null;
		int imageStartNum = 0;
		int imageEndNum = 0;
		try {
			input = new FileInputStream(applicationHome+"application.properties");
			applicationProp.load(input);
			if(applicationProp.getProperty(AnniversaryConstants.DEPLOYMENT_TYPE).equalsIgnoreCase("local")) {
				imageFileLocation = applicationProp.getProperty(AnniversaryConstants.LOCAL_IMAGE_DIR);
			} else if(applicationProp.getProperty(AnniversaryConstants.DEPLOYMENT_TYPE).equalsIgnoreCase("server")) {
				imageFileLocation = applicationProp.getProperty(AnniversaryConstants.SERVER_IMAGE_DIR);
			} else {
				System.out.println("[ERROR] Specify correct deployment type in application.properties");
			}
			
			imageStartNum = new Integer(applicationProp.getProperty(AnniversaryConstants.IMAGE_START_INDEX)).intValue();
			imageEndNum = new Integer(applicationProp.getProperty(AnniversaryConstants.IMAGE_END_INDEX)).intValue();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			
			if(input!=null) {
				
				try{
					input.close();
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		
		int fileNum = randInt(imageStartNum, imageEndNum);
				
		String fileName = imageFileLocation + new Integer(fileNum).toString() + ".png";
		return fileName;
	}
	
	private int randInt(int min, int max) {
		
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) +1)+ min;
		return randomNum;
	}

}
