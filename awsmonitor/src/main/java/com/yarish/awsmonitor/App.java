package com.yarish.awsmonitor;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yarish.awsmonitor.model.Region;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		HashMap<String, String> regions = Region.getRegions();
		logger.debug("regions=\n" + regions);
		logger.info("regions=\n" + regions);
	}

}
