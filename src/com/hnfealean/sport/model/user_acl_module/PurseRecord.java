package com.hnfealean.sport.model.user_acl_module;
/**
 * @hibernate.class table="t_purserecord"
 * 钱包使用记录
 * 
 * @author Administrator
 *
 */
public class PurseRecord {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private boolean inOrOut;
	
	/**
	 * @hibernate.property
	 */
	private String changeMethod;//变更方式：如alipay、paypal等
	
	/**
	 * @hibernate.property
	 */
	private String changeDealRecordNum;//变更交易记录号
	
	/**
	 * @hibernate.property
	 */
	private float changeAmount;//变更金额
	/**
	 * @hibernate.many-to-one
	 */
	private Purse purse;

	public Purse getPurse() {
		return purse;
	}

	public void setPurse(Purse purse) {
		this.purse = purse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(boolean inOrOut) {
		this.inOrOut = inOrOut;
	}

	public String getChangeMethod() {
		return changeMethod;
	}

	public void setChangeMethod(String changeMethod) {
		this.changeMethod = changeMethod;
	}

	public String getChangeDealRecordNum() {
		return changeDealRecordNum;
	}

	public void setChangeDealRecordNum(String changeDealRecordNum) {
		this.changeDealRecordNum = changeDealRecordNum;
	}

	public float getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(float changeAmount) {
		this.changeAmount = changeAmount;
	}
	
}
