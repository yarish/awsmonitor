package com.yarish.awsmonitor;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.yarish.awsmonitor.model.Region;

public class App {
	private static final Logger LOG = Logger.getLogger(App.class.getName());

	public static void main(String[] args) {
		LOG.info("info");
		HashMap<String, String> regions = Region.getRegions();
		LOG.info("regions=" + regions);
	}

}
