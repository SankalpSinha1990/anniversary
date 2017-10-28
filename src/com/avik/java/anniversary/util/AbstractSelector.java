package com.avik.java.anniversary.util;

public abstract class AbstractSelector {
	
	//Set application home - ideally set as environment variable APP_HOME and get it from native java System class
	private static String applicationHome = "C:\\devlopment\\anniversary\\";

	public static String getApplicationHome() {
		return applicationHome;
	}
	
}
