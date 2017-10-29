package com.avik.java.anniversary.constants;

public interface AnniversaryConstants {
	
	//Location designators (named constants) - Keys of the application.properties
	public static final String DEPLOYMENT_TYPE = "deploymenttype";
	public static final String LOCAL_DATA_DIR = "localdatadir";
	public static final String LOCAL_IMAGE_DIR = "localimagedir";
	public static final String SERVER_DATA_DIR = "serverdatadir";
	public static final String SERVER_IMAGE_DIR = "serverimagedir";
	
	//Image file selection designators (named constants) - Other keys in the application.properties
	public static final String IMAGE_START_INDEX = "imagestartindex";
	public static final String IMAGE_END_INDEX = "imageendindex";
	
	//Email properties designators (named constants) - Keys of the email.properties
	public static final String EMAIL_FROM = "from";
	public static final String EMAIL_PASSWORD = "password";
	public static final String EMAIL_CC = "cc";
	public static final String EMAIL_SMTP_HOST = "mail.smtp.host";
	public static final String EMAIL_SMTP_PORT = "mail.smtp.port";
	public static final String EMAIL_SMTP_AUTHENTICATION = "mail.smtp.auth";
	public static final String EMAIL_SMTP_STARTTLS_ENABLED = "mail.smtp.starttls.enable";
	
}
