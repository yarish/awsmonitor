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
import com.yarish.awsmonitor.model.EmailSetting;

public class SendMailTLS {

  private static final Logger logger = LoggerFactory.getLogger(SendMailTLS.class);

  Properties properties;

  public SendMailTLS() {
    EmailSetting emailSettings = new EmailSetting();
    properties = emailSettings.loadProperties();
  }

  public void sendEmail(String body) {

    final String gmail_username = properties.getProperty("gmail.username");
    final String gmail_password = properties.getProperty("gmail.password");
    final String mail_smtp_auth = properties.getProperty("mail.smtp.auth");
    final String mail_smtp_starttls_enable = properties.getProperty("mail.smtp.starttls.enable");
    final String mail_smtp_host = properties.getProperty("mail.smtp.host");
    final String mail_smtp_port = properties.getProperty("mail.smtp.port");
    final String gmail_from_address = properties.getProperty("gmail.from.address");
    final String gmail_to_address = properties.getProperty("gmail.to.address");
    final String gmail_subject = properties.getProperty("gmail.subject");
    final String gmail_body_header = properties.getProperty("gmail.body.header");
    final String gmail_body_main = properties.getProperty("gmail.body.main");
    final String gmail_body_footer = properties.getProperty("gmail.body.footer");

    Properties props = new Properties();
    props.put("mail.smtp.auth", mail_smtp_auth);
    props.put("mail.smtp.starttls.enable", mail_smtp_starttls_enable);
    props.put("mail.smtp.host", mail_smtp_host);
    props.put("mail.smtp.port", mail_smtp_port);

    Session session = Session.getInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(gmail_username, gmail_password);
      }
    });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(gmail_from_address));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(gmail_to_address));
      message.setSubject(gmail_subject);
      message.setText(body);
      Transport.send(message);

    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }

  }
}
