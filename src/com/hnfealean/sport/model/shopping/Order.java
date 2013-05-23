package com.hnfealean.sport.model.shopping;

import java.util.Date;
import java.util.Set;

import com.hnfealean.sport.model.user_acl_module.User;

/**
 *  @hibernate.class table="t_order"
 * @author Administrator
 */
public class Order {
	public void setFee(){
		float productPrice=0.0f;
		for(OrderItem item:this.items){
			productPrice+=item.getAmount()*item.getProductPrice();
		}
		this.totalPrice = productPrice;
		this.totalOrderPrice = productPrice+this.deliverFee;
		this.payAbleFee = this.totalOrderPrice ;
	}
	public void changePayAbleFee(float fee){
		this.payAbleFee = fee;
	}
	/**
	 * @hibernate.id generator-class="uuid"
	 */
	private String orderId;
	
	/**订单--用户 1-1
	 * @hibernate.many-to-one 	
 	 *	column="userId" not-null="true"
	 *  class="com.hnfealean.sport.model.user_acl_module.User"
	 */
	private User user;

	/**
	 * @hibernate.property
	 */
	private Date createDate = new Date();
	/**
	 * @hibernate.property
	 */	
	private String orderState;
	/**
	 * @hibernate.property
	 * 订单合计
	 */	
	private float totalOrderPrice;
	/**
	 * @hibernate.property
	 * 配送费
	 */	
	private float deliverFee;
	/**
	 * @hibernate.property
	 * 商品合计
	 */	
	private float totalPrice;
	/**
	 * @hibernate.property
	 * 应付金额
	 */	
	private float payAbleFee;
	/**
	 * @hibernate.property
	 */	
	private String message;

	/**
	 * @hibernate.property
	 */	
	private boolean paymentState;
	/**
	 * @hibernate.many-to-one	
	 * 	 column="paymentAndDeliverMethod"
	 * class="com.hnfealean.sport.model.shopping.PaymentAndDeliverMethod"
	 */		
	private PaymentAndDeliverMethod paymentAndDeliverMethod;
	
	/**
	 * @hibernate.many-to-one	 
	 * 	 column="deliverInfoId" not-null="true"
	 *  class="com.hnfealean.sport.model.shopping.DeliverInfo"
	 */	
	private DeliverInfo deliverInfo;
	/**
	 * @hibernate.property length="20"
	 */	
	private String deliverMethod;

	/**
	 * @hibernate.set order-by="id asc"  
	 * @hibernate.key column="orderId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.shopping.OrderItem"	
	 */
	private Set<OrderItem> items;
	/**
	 * @hibernate.property length="25"
	 */
	private String administratorName;
	/**
	 * @hibernate.property length="254"
	 */
	private String timeLimit;
	public String getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getAdministratorName() {
		return administratorName;
	}
	public void setAdministratorName(String administratorName) {
		this.administratorName = administratorName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public User getUser() {
		return user;
	}
	public Set<OrderItem> getItems() {
		return items;
	}
	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDeliverMethod() {
		return deliverMethod;
	}
	public void setDeliverMethod(String deliverMethod) {
		this.deliverMethod = deliverMethod;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public float getTotalOrderPrice() {
		return totalOrderPrice;
	}
	public void setTotalOrderPrice(float totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}
	public float getDeliverFee() {
		return deliverFee;
	}
	public void setDeliverFee(float deliverFee) {
		this.deliverFee = deliverFee;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public float getPayAbleFee() {
		return payAbleFee;
	}
	public void setPayAbleFee(float payAbleFee) {
		this.payAbleFee = payAbleFee;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isPaymentState() {
		return paymentState;
	}
	public boolean getPaymentState() {
		return paymentState;
	}
	public void setPaymentState(boolean paymentState) {
		this.paymentState = paymentState;
	}
	public DeliverInfo getDeliverInfo() {
		return deliverInfo;
	}
	public void setDeliverInfo(DeliverInfo deliverInfo) {
		this.deliverInfo = deliverInfo;
	}
	public PaymentAndDeliverMethod getPaymentAndDeliverMethod() {
		return paymentAndDeliverMethod;
	}
	public void setPaymentAndDeliverMethod(
			PaymentAndDeliverMethod paymentAndDeliverMethod) {
		this.paymentAndDeliverMethod = paymentAndDeliverMethod;
	}
	

}
