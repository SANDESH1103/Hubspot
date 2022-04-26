package com.bank.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {
	Properties pro;

	public ReadConfig() {
		// derive path
		File srcFile = new File("./Configuration/config.properties");
		try {
			// open file in read mode
			FileInputStream fileInputStream = new FileInputStream(srcFile);
			pro = new Properties();
			// load the file
			pro.load(fileInputStream);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public String getApplicationUrl() {
		String urlString = pro.getProperty("url");
		return urlString;
	}

	public String getUsername() {
		String usernameString = pro.getProperty("username");
		return usernameString;
	}

	public String getPassword() {
		String passwordString = pro.getProperty("password");
		return passwordString;
	}

	public String getChromePath() {
		String chromeString = pro.getProperty("chromepath");
		return chromeString;
	}

	public String getFirefoxPath() {
		String fireString = pro.getProperty("firefoxpath");
		return fireString;
	}

}
