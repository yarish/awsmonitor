package com.yarish.awsmonitor.model;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrettyPrinter {
  private static final Logger LOG = LoggerFactory.getLogger(PrettyPrinter.class);
  static Properties properties = new Properties();
  private static final String BLANKLINE = "\n\n";
  private static final String UNDERLINE =
      "---------------------------------------------------------------------------------------------------------------------\n";
  private static final String HALF_UNDERLINE = "---------------------------------------";

  public PrettyPrinter() {
    properties = new EmailSetting().loadProperties();
  }


  public static String addHeader() {
    return properties.getProperty("gmail.body.header");
  }

  public static String addFooter() {
    return properties.getProperty("gmail.body.footer");
  }

  public static String printVM(String region, List<VirtualMachine> listOfVMs) {

    StringBuffer buffer = new StringBuffer();
    buffer.append(BLANKLINE).append(HALF_UNDERLINE).append(region).append(HALF_UNDERLINE);

    for (VirtualMachine vmResult : listOfVMs) {
      buffer.append(vmResult.getTag().trim() + "           | " + vmResult.getInstanceId().trim() + "               | "
          + vmResult.getInstanceType().trim() + "               | " + vmResult.getInstanceState().trim()
          + "         \n");
    }


    return buffer.toString();
  }


  public static String print(List<String> bodyfragements) {

    String result = "";
    for (String string : bodyfragements) {
      result = result + string;
    }
    return result;

  }



}
