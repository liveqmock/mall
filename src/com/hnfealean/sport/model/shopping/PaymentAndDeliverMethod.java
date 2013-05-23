package com.hnfealean.sport.model.shopping;
/**
 * @hibernate.class table="t_payment_and_deliver_method"
 * @author Administrator
 *
 */
public class PaymentAndDeliverMethod {
	/**
	 * @hibernate.id 	generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private int deliverMethod;
	/**
	 * @hibernate.property
	 */
	private int timeLimit ;
	/**
	 * @hibernate.property
	 */
	private String messageRequire;
	/**
	 * @hibernate.property
	 */
	private String deliverTypeName;
	/**
	 * @hibernate.property
	 */
	private int paymentMethod ;
	/**
	 * @hibernate.property
	 */
	private String paymentTypeName;
	public String getPaymentTypeName() {
		return paymentTypeName;
	}
	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
	public int getDeliverMethod() {
		return deliverMethod;
	}
	public void setDeliverMethod(int deliverMethod) {
		this.deliverMethod = deliverMethod;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getMessageRequire() {
		return messageRequire;
	}
	public void setMessageRequire(String messageRequire) {
		this.messageRequire = messageRequire;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public int getId() {
		return id;
	}
	public String getDeliverTypeName() {
		return deliverTypeName;
	}
	public void setDeliverTypeName(String deliverTypeName) {
		this.deliverTypeName = deliverTypeName;
	}
	public void setId(int id) {
		this.id = id;
	}
}
