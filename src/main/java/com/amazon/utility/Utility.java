package com.amazon.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {

	static Properties property;
	static FileInputStream file;
	static {
		property = new Properties();
	}
	/**
	 * @throws IOException
	 * @param key : refer the value in properties file .
	 */
	public static Object fetchPropertyValue(String key) throws IOException {
		file = new FileInputStream("./config/config.properties");
		property.load(file);
		return property.get(key);
	}

	

}
