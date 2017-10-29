package com.avik.java.anniversary.messaging;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.avik.java.anniversary.constants.AnniversaryConstants;
import com.avik.java.anniversary.model.AnniversaryMessage;
import com.avik.java.anniversary.util.AbstractSelector;
import com.avik.java.anniversary.util.ImageSelector;


public class MessageSender {

	
	public void sendMessage(AnniversaryMessage message) {
	
		String applicationHome = AbstractSelector.getApplicationHome();
		
		Properties prop = new Properties();
		InputStream input = null;
		Properties emailProperties = new Properties();
		InputStream emailInput = null;
		
		//Initializing email properties
		String from = "";
		String password = "";
		String cc = "";
		String host = "";
		String port = "";
		String auth = "";
		String starttls = "";
		
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
			
			emailInput = new FileInputStream(dataFileLocation+"email.properties");
			emailProperties.load(emailInput);
			
			//Setting the email properties
			from = emailProperties.getProperty(AnniversaryConstants.EMAIL_FROM);
			password = emailProperties.getProperty(AnniversaryConstants.EMAIL_PASSWORD);
			cc = emailProperties.getProperty(AnniversaryConstants.EMAIL_CC);
			host = emailProperties.getProperty(AnniversaryConstants.EMAIL_SMTP_HOST);
			port = emailProperties.getProperty(AnniversaryConstants.EMAIL_SMTP_PORT);
			auth = emailProperties.getProperty(AnniversaryConstants.EMAIL_SMTP_AUTHENTICATION);
			starttls = emailProperties.getProperty(AnniversaryConstants.EMAIL_SMTP_STARTTLS_ENABLED);
			
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
		
		//final String from = "avikdeb.select@gmail.com";
		//final String password = "welcome2gmail";
		//final String cc = "avikdeb@gmail.com";
		//final String to = message.getRecipient();
		
		final String to = message.getRecipient();
		final String finalizedFrom = from;
		final String finalizedPassword = password;
		
		Properties props = new Properties();
		//props.put("mail.smtp.host", "smtp.gmail.com");
		//props.put("mail.smtp.port", "587");
		//props.put("mail.smtp.auth", "true");
		//props.put("mail.smtp.starttls.enable", "true");
		
		props.put(AnniversaryConstants.EMAIL_SMTP_HOST, host);
		props.put(AnniversaryConstants.EMAIL_SMTP_PORT, port);
		props.put(AnniversaryConstants.EMAIL_SMTP_AUTHENTICATION, auth);
		props.put(AnniversaryConstants.EMAIL_SMTP_STARTTLS_ENABLED, starttls);
		
		try {
			
			String subject = message.getSubject();
			
			//HTML Text Part
			MimeMultipart multipart = new MimeMultipart("related");
			BodyPart messageBodyPart = new MimeBodyPart();
			String messageBody = message.getMessageBody();
			messageBodyPart.setContent(messageBody, "text/html");
			multipart.addBodyPart(messageBodyPart);
			
			//Adding the second part - image
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(new ImageSelector().getRandomImageFileName());
			
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setHeader("Content-Type", "image/png");
			multipart.addBodyPart(messageBodyPart);
			
			
			//Session session = Session.getDefaultInstance(props);
			Session session = Session.getInstance(props,
					new Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(finalizedFrom, finalizedPassword);
						}
					});
			
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UFT-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress(from));
			msg.setReplyTo(InternetAddress.parse(to, false));
			msg.setSubject(subject, "UFT-8");
			msg.setContent(multipart);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.addRecipient(RecipientType.CC, new InternetAddress(cc));
			
			System.out.println("Message is ready");
			
			Transport.send(msg);
			
			System.out.println("Email sent successfully");
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
