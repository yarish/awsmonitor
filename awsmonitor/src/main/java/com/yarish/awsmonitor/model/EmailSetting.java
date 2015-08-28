package com.yarish.awsmonitor.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yarish.awsmonitor.App;

public final class EmailSetting {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private Properties properties;

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	protected Properties loadProperties() {
		properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(
					"main/resources/app.properties");
			properties.load(inputStream);
			logger.info("gmail.username="
					+ properties.getProperty("gmail.username"));

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("file not found exception" + e);
		}
		return properties;

	}

}
