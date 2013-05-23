package com.hnfealean.sport.web.forms.product;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ImagesAndStyleActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	
	private int[] ids;

	private FormFile imageFile;

	private String imageUrl;

	private String name;

	private int productId;

	private boolean visible = true;

	public int getId() {
		return id;
	}

	public int[] getIds() {
		return ids;
	}

	public FormFile getImageFile() {
		return imageFile;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getName() {
		return name;
	}
	
	public int getProductId() {
		return productId;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	
	public void setImageFile(FormFile imageFile) {
		this.imageFile = imageFile;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
