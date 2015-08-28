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
    String header = properties.getProperty("gmail.body.header") + properties.getProperty("gmail.body.header_underline");
    return header;
  }

  public static String addFooter() {
    return properties.getProperty("gmail.body.footer");
  }

  public static String printVM(String region, List<VirtualMachine> listOfVMs) {

    StringBuffer buffer = new StringBuffer();
    buffer.append(BLANKLINE).append(HALF_UNDERLINE).append(region).append(HALF_UNDERLINE).append("\n");

    for (VirtualMachine vm : listOfVMs) {
      buffer.append(vm.getTag().trim() + "           | " + vm.getInstanceId().trim() + "               | "
          + vm.getInstanceType().trim() + "               | " + vm.getInstanceState().trim() + "         \n\n");
    }
    if (listOfVMs.size() == 0) {
      buffer.append("\t\t No Virtual Machines are running...\n\n");
    }

    LOG.info(buffer.toString());
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
