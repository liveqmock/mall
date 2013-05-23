package com.hnfealean.sport.model.user_acl_module;

/**
 * @hibernate.class table="t_role"
 * @author Administrator
 *
 */
public class Role {

	/**
	 * 角色ID
	 * @hibernate.id 
	 * 		generator-class="native"
	 */
	private int id;
	
	/**
	 * 角色名称
	 * @hibernate.property 
	 * 		not-null="true" unique="true"
	 */
	private String roleName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Role() {
		super();
	}
	public Role(int id) {
		super();
		this.id = id;
	}
}
