package com.yarish.awsmonitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.ec2.AmazonEC2;
import com.yarish.awsmonitor.email.SendMailTLS;
import com.yarish.awsmonitor.model.AWSAccount;
import com.yarish.awsmonitor.model.PrettyPrinter;
import com.yarish.awsmonitor.model.Region;
import com.yarish.awsmonitor.model.VirtualMachine;

public class App {
  private static final Logger LOG = LoggerFactory.getLogger(App.class);
  private final static Integer SLEEP_TIME = 1000 * 60 * 10; // 5 min
  static AmazonEC2 ec2 = null;
  static AWSAccount awsAccount;

  public static void main(String[] args) {

    String endpointURL = null;
    int totalVMCount = 0;
    PrettyPrinter prettyPrinter = new PrettyPrinter();

    String fragement = "";
    List<String> bodyfragements = new ArrayList<String>();


    HashMap<String, String> regionsMap = Region.getRegions();
    LOG.info("regions mpa =\n" + regionsMap);
    Set<String> regions = regionsMap.keySet();
    while (true) { // Infinite loop
      try {

        // form mail body header
        fragement = PrettyPrinter.addHeader();
        bodyfragements.add(fragement);
        LOG.info("bodyfragements=" + bodyfragements);


        for (String region : regions) {// for each region
          LOG.info("Processing ....region=" + region);
          endpointURL = regionsMap.get(region);
          LOG.info("endpoint url=" + endpointURL);
          awsAccount = new AWSAccount();
          ec2 = awsAccount.getAWSClient(endpointURL);

          // get the list of virtual machines running .
          VirtualMachine machine = new VirtualMachine();
          List<VirtualMachine> listOfVMs = machine.getListOfVirtualmachines(ec2);



          // form mail body
          if (listOfVMs.size() > 0) {
            int noOfVMS = listOfVMs.size();
            fragement = PrettyPrinter.printVM(region, listOfVMs);
            bodyfragements.add(fragement);
            totalVMCount = totalVMCount + noOfVMS;
            LOG.info("{} of vms are currently running in this {} region", noOfVMS, region);
          } else {
            totalVMCount = totalVMCount + listOfVMs.size();
            LOG.info("No Virtual machines are currently running on this {}region", region);
          }

          if (listOfVMs.size() > 0) {
            awsAccount.shutdownVMs(listOfVMs, ec2);
          }


        }// end of for each region
        LOG.info("bodyfragements=" + bodyfragements);

        if (totalVMCount == 0) {
          bodyfragements.add("No Virtual machines are currently running on any region");
        }

        // form mail body footer
        fragement = PrettyPrinter.addFooter();
        bodyfragements.add(fragement);


        String completeBody = PrettyPrinter.print(bodyfragements);
        LOG.info("completeBody=\n" + completeBody);
        new SendMailTLS().sendEmail(completeBody);

        ec2.shutdown();
        Thread.sleep(SLEEP_TIME);// for every 10 min run this loop

      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } // end of infinite loop


  }

}
