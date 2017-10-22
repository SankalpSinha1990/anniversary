package com.avik.java.anniversary.test;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

public class TestImageSelector {

	public static int randInt(int min, int max) {
	
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) +1)+ min;
		return randomNum;
	}
	
	public static void main(String[] args) {
		
		int number = randInt(1, 6);
		System.out.println("Selected file: "+ new Integer(number).toString() + ".png");
		
		System.out.println("Today: "+new Date());
		
		System.out.println("Today: "+new Date().getMonth()+"-"+new Date().getDate());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
		//java.time.MonthDay.parse("22-10",formatter);
		System.out.println("Anniversary: "+java.time.MonthDay.parse("01-31",formatter));
		System.out.println("Formatted Today: "+java.time.MonthDay.parse("0"+new Date().getMonth()+"-"+new Date().getDate(),formatter));
	}

}
