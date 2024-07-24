package com.apitest.RestAPI.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//import static ConfigManager.manager;
//import static ConfigManager.manager;

public class ConfigManager {

	private static ConfigManager manager;
	private static final Properties prop=new Properties();
	
	private ConfigManager() throws IOException {
//		InputStream inputstream=ConfigManager.class.getResourceAsStream("../resources/config.properties");
//		if (inputstream == null) {
//			throw new IOException("Configuration file 'config.properties' not found in the classpath");
//		}
//		prop.load(inputstream);
		String configFilePath = "config.properties";
		InputStream inputStream = null;

		// First try to load from project root
		try {
			inputStream = new FileInputStream(configFilePath);
		} catch (IOException e) {
			// If not found in project root, try to load from classpath
			inputStream = getClass().getClassLoader().getResourceAsStream(configFilePath);
			if (inputStream == null) {
				throw new IOException("Configuration file 'config.properties' not found in project root or classpath");
			}
		}

		try (InputStream is = inputStream) {
			prop.load(is);
		}
	}
	
	public static ConfigManager getInstance()  {
		if(manager==null) {
			synchronized(ConfigManager.class) { //Only one thread of instance is created

                try {
                    manager=new ConfigManager();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                // TODO Auto-generated catch block

			}}

		return manager;
	}


	public String getString(String Key) {
		return System.getProperty(Key,prop.getProperty(Key));
	}
}
