package com.hnfealean.sport.model.user_acl_module;

/**
 * @hibernate.class table="t_addministratorroles"
 * @author Administrator
 *
 */
public class AdministratorRoles {
	
	/**
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	
	/**
	 * 对应的角色
	 * @hibernate.many-to-one column="roleId"
	 */
	private Role role;
	
	/**
	 * 对应的用户
	 * @hibernate.many-to-one
	 */
	private Administrator administrator;
	
	/**
	 * 该角色在该用户中的优先级
	 * @hibernate.property
	 */
	private int orderNo=0;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public AdministratorRoles() {
		super();
	}
	
	public AdministratorRoles(int id) {
		super();
		this.id = id;
	}
}
