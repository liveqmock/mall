package com.hnfealean.sport.web.forms.easypopulate;


import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class EasyPopulateForm extends ActionForm {
	public static String CATEGORY="category";
	public static String PRODUCT="product";
	public static String IMAGE="image";
	public static String ATTRIBUTE="attribute";
	public static String QUANTITY="quantity";
	public static String COMMEND="commend";
	public static String RELATED="related";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private String type;
	private FormFile file;


	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
