package com.hnfealean.sport.web.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class SystemCofig {
	private String path = "/resource/app_config.properties";
	public InputStream getResource(){
	    
		System.out.println(this.getClass().getResource("").getPath());
		return this.getClass().getResourceAsStream(path);
	}
	private static String getProperty(String property){
			 String configFile = "config.ini";//该文件默认位于.project文件同级目录下

		     Properties p = new Properties();
		     try {
		       p.load(new FileInputStream(configFile));
		     } catch (FileNotFoundException e) {
		      e.printStackTrace();
		     } catch (IOException e) {
		       e.printStackTrace();
		     }
		     String value = p.getProperty(property);
				  return value;
		  
		 }
}
