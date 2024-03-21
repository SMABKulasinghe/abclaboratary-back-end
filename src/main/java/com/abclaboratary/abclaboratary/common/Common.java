package com.abclaboratary.abclaboratary.common;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Common {
	
	public boolean sendEMail(List<String> to, String subject, String body) {
		try {

			Properties properties = System.getProperties();
			boolean debug = false;
		
			String email ="abclaboratory.lab@gmail.com";
			String Password ="ogal ljpi eywu evru";
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, Password);
				}
			});

			Message message = new MimeMessage(session);

			message.addFrom(new InternetAddress[] { new InternetAddress("abclaboratory.lab@gmail.com") });


			for (String string : to) {
			
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(string));
			}
			
			message.setSubject(subject);
			message.setContent(body, "text/html");
			
			Transport.send(message);

			System.out.println("Done");

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
