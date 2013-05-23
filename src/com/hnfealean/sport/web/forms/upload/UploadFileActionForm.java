package com.hnfealean.sport.web.forms.upload;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class UploadFileActionForm extends ActionForm {
	
	private int[] id;
	


	public int[] getId() {
		return id;
	}

	public void setId(int[] id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public FormFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	private String name;
	
	private FormFile uploadFile;
	
//	private static Properties properties = new Properties();	
//	public static Properties getProperties() {
//		return properties;
//	}
//
//	public static void setProperties(Properties properties) {
//		UploadFileActionForm.properties = properties;
//	}
//	static{
//		try {
//			properties.load(ValidateFile.class.getClassLoader().getResourceAsStream("allowuploadfiletype.properties"));
//		} catch (IOException e) {
//			e.printStackTrace();
//			
//		}
//	}
//
//	public boolean validateFileType(String fileNameUrl,FormFile File){
//
//		if(!(File.getFileSize()>0) || fileNameUrl != null){
//
//			return false;
//
//		}
//		if(properties.containsKey(fileNameUrl) && properties.get(fileNameUrl).equals(File.getContentType())){
//			
//			return true;
//		}
//
//		return false;
//	}		
//
//	//检查图片是否合法,检查图片的格式和图片的地址格式
//	public boolean validateImageFileType(String imageNameUrl,FormFile imageFile){
//
//		if(imageFile.getFileSize()>0 && imageFile != null){
//			if(imageFile.getContentType().toLowerCase().contains("image")){
//				return true;
//			}
//		}
//		return false;
//	}
}