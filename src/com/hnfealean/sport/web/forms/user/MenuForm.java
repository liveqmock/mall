package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class MenuForm extends ActionForm {

	private int id;//管理员ID
	private int menuItemId;
	
	private int menuGroupId;
	
	private int[] ids;
	
	private String name;
	
	private int orderNo;
	
	private String link;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMenuItemId() {
		return menuItemId;
	}

	public void setMenuItemId(int menuItemId) {
		this.menuItemId = menuItemId;
	}

	public int getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(int menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
}
