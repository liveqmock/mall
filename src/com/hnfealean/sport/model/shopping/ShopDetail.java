package com.hnfealean.sport.model.shopping;

import java.util.Date;

import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.user_acl_module.ContactInfo;
import com.hnfealean.sport.model.user_acl_module.User;

/**
 * @hibernate.class table="t_shopdetail"
 * @author Administrator
 *
 */
public class ShopDetail {
	
	/**
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	

    public ContactInfo getContactInfoId() {
		return contactInfoId;
	}

	public void setContactInfoId(ContactInfo contactInfoId) {
		this.contactInfoId = contactInfoId;
	}

	/**
     * 所属用户
     * @hibernate.many-to-one column="userId"
     */
	private User userId;
	
	/**
	 * 所购买的商品
	 * 		明显：购物明细---商品
	 *					1--1，单向映射 				
	 * @hibernate.many-to-one 
	 */
	private Product productId;
	
	
	/**
	 * 购物时间
	 * @hibernate.property
	 */
	private Date shopTime;
	
	/**
	 * 收货地址
	 *  	明显：购物明细---收货地址
	 *					1---1，单向映射,直接在一个1的一端加many-to-one指向另一个1的一端
	 *@hibernate.many-to-one  			
	 */
	private ContactInfo contactInfoId;
	
	/**
	 * 购买的单价
	 * @hibernate.property
	 */
	private double acceptPrice;
	
	/**
	 * 享受的折扣
	 * @hibernate.property
	 */
	private double discount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUserId() {
		return userId;
	}

	public void setUserId(User userId) {
		this.userId = userId;
	}

	public Product getProductId() {
		return productId;
	}

	public void setProductId(Product productId) {
		this.productId = productId;
	}

	public Date getShopTime() {
		return shopTime;
	}

	public void setShopTime(Date shopTime) {
		this.shopTime = shopTime;
	}

	
	public double getAcceptPrice() {
		return acceptPrice;
	}

	public void setAcceptPrice(double acceptPrice) {
		this.acceptPrice = acceptPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
}
