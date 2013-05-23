package com.hnfealean.sport.web.forms.shopping;

import org.apache.struts.action.ActionForm;

public class ShoppingCartItemForm extends ActionForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int productId;
	
	private int imagesAndStyleId;
	private int[] option;
	private int amount;
	private int sizeId; 
	private int[] amounts;

	public int[] getOption() {
		return option;
	}

	public void setOption(int[] option) {
		this.option = option;
	}

	private String directUrl; 
	public String getDirectUrl() {
		return directUrl;
	}

	public void setDirectUrl(String directUrl) {
		
		this.directUrl =directUrl;
	}

	public int[] getAmounts() {
		return amounts;
	}

	public void setAmounts(int[] amounts) {
		this.amounts = amounts;
	}

	public int getSizeId() {
		return sizeId;
	}

	public void setSizeId(int sizeId) {
		this.sizeId = sizeId;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getImagesAndStyleId() {
		return imagesAndStyleId;
	}

	public void setImagesAndStyleId(int imagesAndStyleId) {
		this.imagesAndStyleId = imagesAndStyleId;
	}
}
