package com.hnfealean.sport.web;

import java.io.IOException;
import java.util.Properties;

public class SiteUrl {

	private static Properties pro = new Properties();
	static{
		try {
			pro.load(SiteUrl.class.getClassLoader().getResourceAsStream("siteurl.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String readUrl(String key){
		return (String) pro.get(key);
	}
}
