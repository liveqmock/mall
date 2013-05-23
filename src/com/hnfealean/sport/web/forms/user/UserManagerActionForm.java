package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class UserManagerActionForm extends ActionForm {

	private int userId;
	
	private int[] userIds;
	
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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int[] getUserIds() {
		return userIds;
	}

	public void setUserIds(int[] userIds) {
		this.userIds = userIds;
	}
	
	
}
