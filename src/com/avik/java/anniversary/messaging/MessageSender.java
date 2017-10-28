package com.avik.java.anniversary.messaging;

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

import com.avik.java.anniversary.model.AnniversaryMessage;
import com.avik.java.anniversary.util.ImageSelector;


public class MessageSender {

	
	public void sendMessage(AnniversaryMessage message) {
	
		final String from = "avikdeb.select@gmail.com";
		final String password = "welcome2gmail";
		final String to = message.getRecipient();
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
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
							return new PasswordAuthentication(from, password);
						}
					});
			
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UFT-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			msg.setFrom(new InternetAddress("avikdeb.select@gmail.com"));
			msg.setReplyTo(InternetAddress.parse("avikdeb.select@gmail.com", false));
			msg.setSubject(subject, "UFT-8");
			msg.setContent(multipart);
			msg.setSentDate(new Date());
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.addRecipient(RecipientType.CC, new InternetAddress("avikdeb@gmail.com"));
			
			System.out.println("Message is ready");
			
			Transport.send(msg);
			
			System.out.println("Email sent successfully");
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
