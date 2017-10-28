package com.avik.java.anniversary.test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class TestMessageSelector {
		
	private static int randInt(int min, int max) {
	
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) +1)+ min;
		return randomNum;
	}
	
	public static String getRandomMessage() {
		
		String message = "";
		String filepath = "C:\\devlopment\\anniversary\\data\\";
		Properties prop = new Properties();
		InputStream input = null;
		
		try {
			
			input = new FileInputStream(filepath+"messages.properties");
			prop.load(input);
			
			String msgIndex = new Integer(randInt(1, prop.size())).toString();
			message = prop.getProperty(msgIndex);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(input!=null) {
				try{
					input.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return message;
	}
	
	public static void main(String[] args) {
		
		System.out.println(getRandomMessage());
		
	}

}
