package com.hnfealean.sport.web.config;

import java.util.Properties;

public class Constant {
		private static Constant con = null;

	    private static Properties proCache = new Properties();

	    static {
	        con = new Constant();
	    }

	    public Constant() {

	        try {
	        	SystemCofig systemConfig = new SystemCofig();
	            proCache.load(systemConfig.getResource());
	        } catch (Exception e) {
	            System.out.println("---Constant--" + e.toString());
	        }
	    }

	    public static String getValue(String key) throws Exception {
	        if (proCache.containsKey(key)) {
	            return (String) proCache.get(key);
	        } else {
	            throw new Exception("Unable to locate the Property with key = '" + key + "'");
	        }
	    }

	    public static String getNotNullValue(String key) {
	        try {
	            return getValue(key);
	        } catch (Exception e) {
	            return "";
	        }
	    }
}
