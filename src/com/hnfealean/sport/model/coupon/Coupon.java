package com.hnfealean.sport.model.coupon;

/**
 * @hibernate.class table="t_coupon"
 * @author Administrator
 *
 */
public class Coupon {

	/**
	 * @hibernate.id generator-class="assigned" length="64"
	 */
	private String id;
	
	/**
	 * @hibernate.property length="64"
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private float discountPrice;
	/**
	 * @hibernate.property
	 */	
	private boolean limitTimes;//使用次数限制
	/**
	 * @hibernate.property
	 */	
	private int maxTimes;//最大使用次数
	/**
	 * @hibernate.property
	 */	
	private int usedTimes;//已使用的次数
	/**
	 * @hibernate.property
	 */
	private boolean avaliable;//是否可用
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvaliable() {
		return avaliable;
	}
	public boolean getAvaliable() {
		return avaliable;
	}
	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public float getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
	}
	public boolean getLimitTimes() {
		return limitTimes;
	}
	public boolean isLimitTimes() {
		return limitTimes;
	}
	public void setLimitTimes(boolean limitTimes) {
		this.limitTimes = limitTimes;
	}
	public int getMaxTimes() {
		return maxTimes;
	}
	public void setMaxTimes(int maxTimes) {
		this.maxTimes = maxTimes;
	}
	public int getUsedTimes() {
		return usedTimes;
	}
	public void setUsedTimes(int usedTimes) {
		this.usedTimes = usedTimes;
	}
	
	
}
