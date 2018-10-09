package com.services;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.albany.foodOnWheels.model.FoodTruckOwner;
import com.albany.foodOnWheels.model.User;

public class EmailService {
	
	final String username = "callteam68@gmail.com";
	final String password = "TeamAdmin123";

	public EmailService() {

	}

	public void send(String toUser) {

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toUser));
			message.setSubject("New Food Truck registeration done");
			message.setText("Thanks for connecting");
			
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
