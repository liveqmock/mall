package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class ACLForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5083272468296928084L;

	private int roleId;
	private int aclId;
	private int administratorId;
	private boolean permission;
	//private int[] moduleId;
	
	private int moduleId;
	private int moduleFunctionId;
	
	//private int[] aclState;

	//private String principalType;
	
	//private int principalSn;
	
	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public int getModuleFunctionId() {
		return moduleFunctionId;
	}

	public void setModuleFunctionId(int moduleFunctionId) {
		this.moduleFunctionId = moduleFunctionId;
	}

	public boolean getPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}

	public int getAclId() {
		return aclId;
	}

	public void setAclId(int aclId) {
		this.aclId = aclId;
	}

	

	

	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getAdministratorId() {
		return administratorId;
	}

	public void setAdministratorId(int administratorId) {
		this.administratorId = administratorId;
	}
	
	
}
