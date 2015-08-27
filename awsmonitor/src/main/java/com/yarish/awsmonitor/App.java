package com.yarish.awsmonitor;

import java.util.logging.Level;
import java.util.logging.Logger;


public class App 
{
	private final static Logger logger = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		logger.setLevel(Level.ALL);
		logger.warning("Hello World!");
	}
	
	
}
