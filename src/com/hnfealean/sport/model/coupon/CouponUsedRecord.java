package com.hnfealean.sport.model.coupon;

import java.util.Date;

/**
 * @hibernate.class table="t_couponusedrecord"
 * @author Administrator
 *
 */
public class CouponUsedRecord {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="64"
	 */
	private String userEmail;
	
	/**
	 * @hibernate.property length="32"
	 */
	private String userName;
	
	/**
	 * @hibernate.property
	 */
	private Date usedDate=new Date();
	
	/**
	 * @hibernate.many-to-one cascade="delete-orphan"
	 */
	private Coupon coupon;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	public Coupon getCoupon() {
		return coupon;
	}

	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}

}
