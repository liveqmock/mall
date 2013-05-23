package com.hnfealean.sport.web.forms.coupon;

import org.apache.struts.action.ActionForm;

public class CouponActionForm extends ActionForm {

	private String id;
	
	private String[] ids;
	
	private float discountPrice;

	private boolean limitTimes;//使用次数限制
	
	private int maxTimes;//最大使用次数

	private int usedTimes;//已使用的次数

	private boolean avaliable;//是否可用
	
	private String name;
	public boolean getLimitTimes() {
		return limitTimes;
	}
	public boolean getAvaliable() {
		return avaliable;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public float getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(float discountPrice) {
		this.discountPrice = discountPrice;
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

	public boolean isAvaliable() {
		return avaliable;
	}

	public void setAvaliable(boolean avaliable) {
		this.avaliable = avaliable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
