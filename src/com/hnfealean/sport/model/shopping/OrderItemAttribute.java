package com.hnfealean.sport.model.shopping;


/**
 * @hibernate.class table="t_orderattribute"
 * @author Administrator
 *
 */
public class OrderItemAttribute {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="20"
	 */
	private String attributeName;
	/**
	 * @hibernate.property length="20"
	 */
	private String attributeValue;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	

}
