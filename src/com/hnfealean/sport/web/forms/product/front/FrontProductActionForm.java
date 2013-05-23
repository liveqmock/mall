package com.hnfealean.sport.web.forms.product.front;

import org.apache.struts.action.ActionForm;

public class FrontProductActionForm extends ActionForm {

	private String url;
	private String orderBy;
	private String filter;
	private String sex;
	private int page;
	private int id;
	private String show;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter.toLowerCase();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url.toLowerCase();
	}

	private int categoryId;
	
	private String brandId;
	
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy.toLowerCase();
	}

}
