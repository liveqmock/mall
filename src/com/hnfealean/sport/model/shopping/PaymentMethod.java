package com.hnfealean.sport.model.shopping;

/**
 * @hibernate.class table="t_payment_method" 
 * @author Administrator
 *
 */
public class PaymentMethod {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="20" not-null="true"
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private boolean enable;
	/**
	 * @hibernate.property length="1000"
	 */
	private String description;
	/**
	 * @hibernate.property length="255"
	 */
	private String accessUrl;
	/**
	 * @hibernate.property
	 */
	private int orderNo;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAccessUrl() {
		return accessUrl;
	}
	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}
}
