package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class AdministratorForm extends ActionForm {
	private static final long serialVersionUID = 9045228585362421089L;

	private int id=0;
	
	private int roleId=0;
	
	private int[] rolesId;
	
	private String name;

	private String newPassword;
	private String password;
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String iniPassword) {
		this.newPassword = iniPassword;
	}

	public int[] getRolesId() {
		return rolesId;
	}

	public void setRolesId(int[] rolesId) {
		this.rolesId = rolesId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
