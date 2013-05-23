package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class UserLoginCheckForm extends ActionForm {
	
	private String username;

	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


}
