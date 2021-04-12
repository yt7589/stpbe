package com.zhuanjingkj.stpbe.ca_tvis;

import com.zhuanjingkj.stpbe.ca_tvis.apps.wxs2102.Wxs2102App;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CaTvisWxs2102Application {
	public static String CONFIG_FOLDER = "config/";
    public static void main(String[] args) {
		System.out.println("Client Application ca-tvis-wxs2102 v0.0.2");
		Wxs2102App app = new Wxs2102App();
		app.startup();
    }

	public static String readProperty(String key) {
		Properties properties = new Properties();
		File configFolder = new File(CONFIG_FOLDER);
		//
		try {
			InputStream inputStream = null;
			if (!configFolder.exists()) {
				inputStream = Object.class.getResourceAsStream("/application.properties");
			} else {
				inputStream = new FileInputStream(new File(CONFIG_FOLDER + "/application.properties"));
			}
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String activeProfile = properties.getProperty("spring.profiles.active");
		try {
			InputStream inStrm = null;
			if (!configFolder.exists()) {
				inStrm = Object.class.getResourceAsStream("/application-" + activeProfile + ".properties");
			} else {
				inStrm = new FileInputStream(new File(CONFIG_FOLDER + "/application-" + activeProfile + ".properties"));
			}
			//
			properties.load(inStrm);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return properties.getProperty(key);
	}
}
