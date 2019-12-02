package net.mrsistemas.healthy.device.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utils {
	private static Properties properties = new Properties();

	public static String getKey(String key) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("D:\\config\\dblite.config");
			properties.load(fis);
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		return properties.getProperty(key);
	}

}
