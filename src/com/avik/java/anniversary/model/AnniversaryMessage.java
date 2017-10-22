package com.avik.java.anniversary.model;

import com.avik.java.anniversary.util.ImageSelector;

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
		String strimage = new ImageSelector().getRandomImageFileName();
		System.out.println("Image selected: "+strimage);
		
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
			 			+"</table>"
		 				+"<br>"
			 			
	 					+"<br>"
	 					+"<table>"
	 						+"<tr>"
	 							+"<td>Best wishes,</td>"
	 						+"</tr>"
	 						+"<tr>"
 								+"<td>"
 									+"DevOps TestLab Team - Gurgaon"
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
