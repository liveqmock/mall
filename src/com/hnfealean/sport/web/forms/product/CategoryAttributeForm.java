package com.hnfealean.sport.web.forms.product;

import org.apache.struts.action.ActionForm;

public class CategoryAttributeForm extends ActionForm{

	private int productId;
	private int[] productIds;
	private int attributeId;
	
	private String name;
	
	private String value;
	
	private int optionId;
	
	private int[] optionIds;
	
	private int[] attributeIds;

	private int categoryId;
	
	private int[] categoryIds;
	
	private String filter;
	
	private String[] filters;
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String[] getFilters() {
		return filters;
	}

	public void setFilters(String[] filters) {
		this.filters = filters;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int[] getProductIds() {
		return productIds;
	}

	public void setProductIds(int[] productIds) {
		this.productIds = productIds;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int[] getCategoryIds() {
		return categoryIds;
	}

	public void setCategoryIds(int[] categoryIds) {
		this.categoryIds = categoryIds;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getOptionId() {
		return optionId;
	}

	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}

	public int[] getOptionIds() {
		return optionIds;
	}

	public void setOptionIds(int[] optionIds) {
		this.optionIds = optionIds;
	}

	public int[] getAttributeIds() {
		return attributeIds;
	}

	public void setAttributeIds(int[] attributeIds) {
		this.attributeIds = attributeIds;
	}
	
	
}
