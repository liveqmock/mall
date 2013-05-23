package com.hnfealean.sport.web;

import java.io.IOException;
import java.util.Properties;


public class WebProperty {
	private static Properties pro = new Properties();
	static {
		try {
			pro.load(WebProperty.class.getClassLoader().getResourceAsStream("siteinfo.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String readUrl(String key){
		return (String) pro.get(key);
	}
}
