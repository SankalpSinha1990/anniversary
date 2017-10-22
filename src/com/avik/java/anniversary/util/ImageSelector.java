package com.avik.java.anniversary.util;

import java.util.Random;

public class ImageSelector {
	
	private int startFileNum = 1;
	private int endFileNum = 6;
	private String filePath = "C:\\devlopment\\anniversary\\images\\";
	
	public ImageSelector() {
		
	}
	
	public String getRandomImageFileName() {
		
		int fileNum = randInt(startFileNum, endFileNum);
		String fileName = filePath + new Integer(fileNum).toString() + ".png";
		return fileName;
	}
	
	private int randInt(int min, int max) {
		
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) +1)+ min;
		return randomNum;
	}

}
