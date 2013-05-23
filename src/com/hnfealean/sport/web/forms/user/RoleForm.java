package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class RoleForm extends ActionForm {

	private static final long serialVersionUID = 7048797923367041921L;

	private int id;
	
	private String roleName;
	
	private String newName;
	private int[] ids;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNewName() {
		return newName;
	}

	public void setNewName(String newName) {
		this.newName = newName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}
	
}
