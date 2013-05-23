package com.hnfealean.sport.web.forms.product;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class BrandActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	private String name;
	
	private String logoUrl;
	
	private FormFile imageFile;

	public FormFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(FormFile imageFile) {
		this.imageFile = imageFile;
	}
}
