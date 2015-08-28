package com.yarish.awsmonitor.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yarish.awsmonitor.App;
import com.yarish.awsmonitor.email.SendMailTLS;

import junit.framework.TestCase;

public class SendMailTLSTest extends TestCase {

  SendMailTLS sendMailTLS;
  private static final Logger logger = LoggerFactory.getLogger(App.class);

  @Override
  protected void setUp() throws Exception {
    logger.info("setUp()");
    sendMailTLS = new SendMailTLS();
  }

  public void testEmailSending() {
    logger.info("testEmailSending");
    assertNotNull(sendMailTLS);
    sendMailTLS.sendEmail("HelloWorld!");

  }

  @Override
  protected void tearDown() throws Exception {
    sendMailTLS = null;
    logger.info("tearDown()");
  }

}
