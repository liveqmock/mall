package com.hnfealean.sport.model.shopping;
/**
 * @hibernate.class table="t_orderstatus"
 * @author Administrator
 *
 */
public class OrderStatus {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="true" length="20"
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
