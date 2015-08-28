package com.yarish.awsmonitor.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.yarish.awsmonitor.App;

public class AWSAccount implements Account {
  private static final Logger LOG = LoggerFactory.getLogger(AWSAccount.class);
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


  public void shutdownVMs(List<VirtualMachine> listOfVMs, AmazonEC2 ec2) {

    List<String> instanceIds = new ArrayList<String>();
    LOG.info("listOfVMs.size()=" + listOfVMs.size());

    for (VirtualMachine vm : listOfVMs) {
      instanceIds.add(vm.getInstanceId().trim());
      LOG.info("vm.getInstanceId().trim()=" + vm.getInstanceId().trim());
    }

    LOG.info("instanceIds=" + instanceIds);

    StopInstancesRequest stopInstancesRequest = new StopInstancesRequest();
    stopInstancesRequest.withInstanceIds(instanceIds);
    ec2.stopInstances(stopInstancesRequest);
  }

}
