package com.avik.java.anniversary.model;

import com.avik.java.anniversary.util.ImageSelector;
import com.avik.java.anniversary.util.MessageSelector;

public class AnniversaryMessage {
	
	private String recipient;
	private String subject;
	private String messageBody;
	
	public AnniversaryMessage createMessage(Person person) {
		
		AnniversaryMessage message = new AnniversaryMessage();
		message.setRecipient(person.getEmail());
		this.subject = "Happy Birthday "+person.getNickname();
		message.setSubject(subject);
		message.setMessageBody(createContent(person));
		
		return message;
	}
	
	private String createContent(Person person) {
		
		String content = "";
		String name = person.getNickname();
		String randomImage = new ImageSelector().getRandomImageFileName();
		String randomContent = new MessageSelector().getRandomMessage();
		System.out.println("Image selected: "+randomImage);
		System.out.println("Content selected: "+randomContent);
		
		content = "<html>"
			 		+"<body>"
			 			+"<table>"
			 				+"<tr>"
			 					+"<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>"
			 				+"</tr>"
			 				+"<tr>"
		 						+"<td><font color=\"#C70039\"><b>Many Many Happy Returns of the Day "+name+" !!!"+"</b></font>"
		 						+"</td>"
		 					+"</tr>"
		 					+"<tr>"
 								+"<td>&nbsp;&nbsp;&nbsp;&nbsp;</font>"
 								+"</td>"
 							+"</tr>"
		 					+"<tr>"
	 							+"<td><font color=\"#2471A3\"><b>"+randomContent+"</b></font>"
	 							+"</td>"
	 						+"</tr>"
			 			+"</table>"
		 				+"<br>"
			 			
	 					+"<br>"
	 					+"<table>"
	 						+"<tr>"
	 							+"<td>Warm Regards,</td>"
	 						+"</tr>"
	 						+"<tr>"
 								+"<td>"
 									+"Team Gurgaon"
 								+"</td>"
 							+"</tr>"
 						+"</table>"
 					+"</body>"				
 				+"</html>";

		return content;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

}
