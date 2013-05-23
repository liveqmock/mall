package com.hnfealean.sport.model.user_acl_module;

import java.util.Set;

/**
 * @hibernate.class table="t_menugroup"
 * @author Administrator
 *
 */
public class MenuGroup {
	public MenuGroup(){
		super();
	}
	public MenuGroup(int id) {
		super();
		this.id = id;
	}

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private String name;
	
	/**
	 * @hibernate.property
	 */
	private int orderNo;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="true" cascade="delete" inverse="true"
	 * @hibernate.key column="menugroupid"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.user_acl_module.MenuItem"
	 */
	private Set<MenuItem> menuItems;
	
	/**
	 * @hibernate.many-to-one class="com.hnfealean.sport.model.user_acl_module.Administrator"
	 */
	private Administrator admin;

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
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

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Set<MenuItem> getMenuItems() {
		return menuItems;
	}

	public void setMenuItems(Set<MenuItem> menuItems) {
		this.menuItems = menuItems;
	}


}
