package com.hnfealean.sport.model.user_acl_module;

/**
 * @hibernate.class table="t_acl"
 * @author Administrator
 *
 */
public class ACL {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private int roleId;
	
	/**
	 * @hibernate.property length="254"
	 */
	private String url;
	
	/**
	 * @hibernate.property 
	 */
	private String moduleName;
	
	/**
	 * @hibernate.property 
	 */
	private String functionName;
	
	/**
	 * @hibernate.property 
	 */
	private String functionDescription;
	
	/**
	 * @hibernate.property
	 */
	private boolean permission;

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getFunctionDescription() {
		return functionDescription;
	}

	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}
}
