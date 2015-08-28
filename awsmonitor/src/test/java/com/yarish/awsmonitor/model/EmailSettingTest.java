package com.yarish.awsmonitor.model;

import java.util.Properties;

import junit.framework.TestCase;

public class EmailSettingTest extends TestCase {

	EmailSetting emailSetting;

	@Override
	protected void setUp() throws Exception {
		emailSetting = new EmailSetting();

	}

	public void testProperyLoadingIntoEmailSettings() {
		Properties properties = emailSetting.loadProperties();
		assertNotNull(properties);
		assertEquals("yarish@gmail.com", properties.get("gmail.username"));
	}

	@Override
	protected void tearDown() throws Exception {
		emailSetting = null;
	}

}
