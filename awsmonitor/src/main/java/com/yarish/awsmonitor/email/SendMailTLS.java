package com.yarish.awsmonitor.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yarish.awsmonitor.App;

public class SendMailTLS {

	private static final Logger logger = LoggerFactory.getLogger(App.class);
	
	public static void main(String[] args) {

		final String username = "yarish@gmail.com";
		final String password = "cisco143$9980044534";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("yarish@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("ykjraman@jamcracker.com"));
			message.setSubject("AWS : Running VM Instances");
			message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean sendEmail(String body) {

		final String username = "yarish@gmail.com";
		final String password = "cisco143$9980044534";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ykjraman@jamcracker.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("ykjraman@jamcracker.com"));
			message.setSubject("AWS : Running VM Instances Monitoring Report");
			String firstLine = "automated email and scripts runs every 6 hours !\n";
			message.setText(firstLine + body);

			Transport.send(message);

			System.out.println("Done");
			return true;

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
}