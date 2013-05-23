package com.hnfealean.sport.model.user_acl_module;

import java.util.Set;

/**
 * @hibernate.class table="t_administrator"
 * @author Administrator
 *
 */
public class Administrator {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property unique="true" not-null="true"
	 */
	private String name;
	
	/**
	 * @hibernate.property not-null="true"
	 */
	private String password;

	/**
	 * @hibernate.set order-by="id asc" lazy="true"
	 * @hibernate.key column="admin"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.user_acl_module.MenuGroup"
	 */
	private Set<MenuGroup> menuGroups;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Administrator(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public Administrator(){
		super();
	}
	public Administrator(int id){
		super();
		this.id = id;	
	}

	public Set<MenuGroup> getMenuGroups() {
		return menuGroups;
	}

	public void setMenuGroups(Set<MenuGroup> menuGroups) {
		this.menuGroups = menuGroups;
	}

}
