package com.yarish.awsmonitor.model;

import java.util.HashMap;

public final class Region {

	private final static HashMap<String, String> regionMap = new HashMap<String, String>();

	
	static { // TODO : load it from proerty file later or get it from google docs 
		regionMap.put("US East (N. Virginia)","https://ec2.us-east-1.amazonaws.com");
		regionMap.put("US West (Oregon)", "https://ec2.us-west-2.amazonaws.com");
		regionMap.put("US West (N. California)","https://ec2.us-west-1.amazonaws.com");
		regionMap.put("EU (Ireland)", "https://ec2.eu-west-1.amazonaws.com");
		regionMap.put("EU (Frankfurt)","https://ec2.eu-central-1.amazonaws.com");
		regionMap.put("Asia Pacific (Singapore)","https://ec2.ap-southeast-1.amazonaws.com");
		regionMap.put("Asia Pacific (Sydney)","https://ec2.ap-southeast-2.amazonaws.com");
		regionMap.put("Asia Pacific (Tokyo)","https://ec2.ap-northeast-1.amazonaws.com");
		regionMap.put("South America (Sao Paulo)","https://ec2.sa-east-1.amazonaws.com");
	}
	
	public final HashMap<String, String> getRegions(){
		return regionMap;
	}

}
