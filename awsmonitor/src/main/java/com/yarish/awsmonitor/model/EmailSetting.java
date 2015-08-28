package com.yarish.awsmonitor.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class EmailSetting {
  private static final Logger LOG = LoggerFactory.getLogger(EmailSetting.class);
  private Properties properties;

  public Properties loadProperties() {
    properties = new Properties();
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream("main/resources/app.properties");
      properties.load(inputStream);
      LOG.info("gmail.username=" + properties.getProperty("gmail.username"));
      LOG.info("mail.smtp.auth=" + properties.getProperty("mail.smtp.auth"));
      LOG.info("gmail.password=" + properties.getProperty("gmail.password"));
      LOG.info("mail.smtp.starttls.enable=" + properties.getProperty("mail.smtp.starttls.enable"));
      LOG.info("mail.smtp.host=" + properties.getProperty("mail.smtp.host"));
      LOG.info("mail.smtp.port=" + properties.getProperty("mail.smtp.port"));
      LOG.info("gmail.from.address=" + properties.getProperty("gmail.from.address"));
      LOG.info("gmail.to.addres=" + properties.getProperty("gmail.to.address"));
      LOG.info("gmail.subject=" + properties.getProperty("gmail.subject"));
      LOG.info("gmail.body.header=" + properties.getProperty("gmail.body.header"));
      LOG.info("gmail.body.main=" + properties.getProperty("gmail.body.main"));
      LOG.info("gmail.body.footer=" + properties.getProperty("gmail.body.footer"));

    } catch (IOException e) {
      e.printStackTrace();
      LOG.error("file not found exception" + e);
    } finally {
      if (inputStream != null) {
        try {
          inputStream.close();
        } catch (IOException e) {
          e.printStackTrace();
          LOG.error("file closing failed");
        }
      }
    }
    return properties;

  }

}
