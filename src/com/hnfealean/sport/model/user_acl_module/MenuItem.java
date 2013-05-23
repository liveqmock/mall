package com.hnfealean.sport.model.user_acl_module;

/**
 * @hibernate.class table="t_menuitem"
 * @author Administrator
 *
 */
public class MenuItem {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="true"
	 */
	private String name;
	
	/**
	 * @hibernate.property not-null="true"
	 */
	private String link;
	
	/**
	 * @hibernate.property
	 */
	private int orderNo;

	/**
	 * @hibernate.many-to-one class="com.hnfealean.sport.model.user_acl_module.MenuGroup" column="menugroupid"
	 */
	private MenuGroup group;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public MenuGroup getGroup() {
		return group;
	}

	public void setGroup(MenuGroup group) {
		this.group = group;
	}
}
