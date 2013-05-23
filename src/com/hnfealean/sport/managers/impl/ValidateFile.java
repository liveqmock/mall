package com.hnfealean.sport.managers.impl;


import java.io.IOException;
import java.util.Properties;

import org.apache.struts.upload.FormFile;

import com.hnfealean.sport.web.SystemException;

public class ValidateFile {
	private static Properties properties = new Properties();	
	public static Properties getProperties() {
		return properties;
	}

	public ValidateFile(){
		
		if(properties == null){
			try {
				properties.load(ValidateFile.class.getClassLoader().getResourceAsStream("AllowFileType.properties"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void setProperties(Properties properties) {
		ValidateFile.properties = properties;
	}
	static{
		try {
			properties.load(ValidateFile.class.getClassLoader().getResourceAsStream("AllowFileType.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}

	public boolean validateFileType(String fileNameUrl,FormFile File){

		if(File.getFileSize()==0 || fileNameUrl == null){
		//	System.out.println("asdsdasdasd");
			//return false;
			throw new SystemException("û���ļ���");
		}
		if(File.getContentType().contains("image")){
			return true;
		}
		if(properties.containsKey(fileNameUrl) && properties.get(fileNameUrl).equals(File.getContentType())){
			
			return true;
		}

		return false;
	}		

	//���ͼƬ�Ƿ�Ϸ�,���ͼƬ�ĸ�ʽ��ͼƬ�ĵ�ַ��ʽ
	public boolean validateImageFileType(String imageNameUrl,FormFile imageFile){

		if(imageFile.getFileSize()>0 && imageFile != null){
			if(imageFile.getContentType().toLowerCase().contains("image")){
				return true;
			}
		}
		return false;
	}
}
