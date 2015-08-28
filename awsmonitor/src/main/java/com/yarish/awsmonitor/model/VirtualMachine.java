package com.yarish.awsmonitor.model;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceState;
import com.amazonaws.services.ec2.model.Reservation;

public class VirtualMachine {

  private String tag;
  private String instanceId;
  private String instanceType;
  private String instanceState;


  public String getTag() {
    return tag;
  }

  public void setTag(String tag) {
    this.tag = tag;
  }

  public String getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  public String getInstanceType() {
    return instanceType;
  }

  public void setInstanceType(String instanceType) {
    this.instanceType = instanceType;
  }

  public String getInstanceState() {
    return instanceState;
  }

  public void setInstanceState(String instanceState) {
    this.instanceState = instanceState;
  }


  public List<VirtualMachine> getListOfVirtualmachines(AmazonEC2 ec2Client) {
    List<VirtualMachine> vmList = new ArrayList<VirtualMachine>();

    DescribeInstancesResult result = ec2Client.describeInstances();
    List<Reservation> reservations = result.getReservations();

    for (Reservation reservation : reservations) {

      List<Instance> instances = reservation.getInstances();

      for (Instance instance : instances) {

        String instanceId = instance.getInstanceId();
        String tag = "";
        if (instance.getTags().size() > 0) {
          tag = instance.getTags().get(0).getValue();
        }
        String instanceType = instance.getInstanceType();
        InstanceState InstanceState = instance.getState();
        String instanceState = InstanceState.getName();
        // Integer code = InstanceState.getCode();

        if (instanceState.equalsIgnoreCase("running") || instanceState.equalsIgnoreCase("shutdown")) {
          VirtualMachine vm = new VirtualMachine();
          vm.setInstanceId(instanceId);
          vm.setTag(tag);
          vm.setInstanceType(instanceType);
          vm.setInstanceState(instanceState);
          vmList.add(vm);
        }

      }

    }
    return vmList;
  }

}
