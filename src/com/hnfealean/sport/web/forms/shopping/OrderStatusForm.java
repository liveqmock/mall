package com.hnfealean.sport.web.forms.shopping;

import org.apache.struts.action.ActionForm;

public class OrderStatusForm extends ActionForm {

	private int id;
	
	private String name;
	
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
