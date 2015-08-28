package com.yarish.awsmonitor.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.yarish.awsmonitor.App;

public class AWSAccount implements Account {
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  private String apiKey;
  private String secretKey;


  public AWSAccount() {

    Properties aws_properties = new Properties();
    InputStream inputStream = null;
    try {
      inputStream = new FileInputStream("main/resources/aws.account.properties");
      aws_properties.load(inputStream);
      apiKey = aws_properties.getProperty("api.key");
      secretKey = aws_properties.getProperty("secret.key");
//      LOG.info("api.key=" + apiKey);
//      LOG.info("secret.key=" + secretKey);

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

  }

  @Override
  public Account getAccount() {
    return new AWSAccount();
  }

  public AmazonEC2 getAWSClient(String enpointURL) {
    BasicAWSCredentials awsCreds = new BasicAWSCredentials(apiKey, secretKey);
    AmazonEC2 ec2 = new AmazonEC2Client(awsCreds);
    ec2.setEndpoint(enpointURL);
    return ec2;
  }

}
