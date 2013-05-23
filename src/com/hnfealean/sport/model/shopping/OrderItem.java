package com.hnfealean.sport.model.shopping;

import java.util.HashSet;
import java.util.Set;

/**
 * @hibernate.class table="t_orderitem"
 * @author Administrator
 *
 */
public class OrderItem {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String productName;
	/**
	 * @hibernate.property
	 */	
	private int productId;
	/**
	 * @hibernate.property
	 */	
	private float productPrice =0;

	/**
	 * @hibernate.property
	 */	
	private int amount;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="extra"
	 * @hibernate.key column="orderItemId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.shopping.OrderItemAttribute"	
	 */
	private Set<OrderItemAttribute> orderItemAttribues = new HashSet<OrderItemAttribute>();
	
	
	public Set<OrderItemAttribute> getOrderItemAttribues() {
		return orderItemAttribues;
	}

	public void setOrderItemAttribues(Set<OrderItemAttribute> orderItemAttribues) {
		this.orderItemAttribues = orderItemAttribues;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	
}
