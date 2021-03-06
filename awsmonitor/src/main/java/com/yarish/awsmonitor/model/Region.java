package com.yarish.awsmonitor.model;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yarish.awsmonitor.App;

public final class Region {

  private static final Logger logger = LoggerFactory.getLogger(App.class);
  private static HashMap<String, String> regionMap = new HashMap<String, String>();
  private String regionName;
  private String endpointUrl;

  public String getRegionName() {
    return regionName;
  }

  public void setRegionName(String regionName) {
    this.regionName = regionName;
  }

  public String getEndpointUrl() {
    return endpointUrl;
  }

  public void setEndpointUrl(String endpointUrl) {
    this.endpointUrl = endpointUrl;
  }

  static { // TODO : load it from proerty file later or get it from google
    // docs
    regionMap.put("US East (N. Virginia)", "https://ec2.us-east-1.amazonaws.com");
    regionMap.put("US West (Oregon)", "https://ec2.us-west-2.amazonaws.com");
    regionMap.put("US West (N. California)", "https://ec2.us-west-1.amazonaws.com");
    regionMap.put("EU (Ireland)", "https://ec2.eu-west-1.amazonaws.com");
    regionMap.put("EU (Frankfurt)", "https://ec2.eu-central-1.amazonaws.com");
    regionMap.put("Asia Pacific (Singapore)", "https://ec2.ap-southeast-1.amazonaws.com");
    regionMap.put("Asia Pacific (Sydney)", "https://ec2.ap-southeast-2.amazonaws.com");
    regionMap.put("Asia Pacific (Tokyo)", "https://ec2.ap-northeast-1.amazonaws.com");
    regionMap.put("South America (Sao Paulo)", "https://ec2.sa-east-1.amazonaws.com");
  }

  public final static HashMap<String, String> getRegions() {
    return regionMap;
  }

}
