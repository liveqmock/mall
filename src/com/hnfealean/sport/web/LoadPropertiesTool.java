package com.hnfealean.sport.web;

import java.io.FileInputStream;
import java.util.Properties;
		public class LoadPropertiesTool{
			private Properties prop=null;
			public void loadProperties(String path){
			prop = new Properties();
			try {
			  prop.load(new FileInputStream(path));
			} catch (Exception e)
			{
			System.out.println("找不到配置文件");
			e.printStackTrace();
			}
			}
			public String getProperties(String key)
			{
			         String value =prop.getProperty(key);
			
			if(value==null)
			         {
			             value = "";
			         }
			        return value.trim();
			    }
} 
