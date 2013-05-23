package com.hnfealean.sport.model.payment.paypal;

import com.hnfealean.sport.model.shopping.Order;

/**
 * @hibernate.class table="t_paypal"
 * @author Administrator
 *
 */
public class Paypal {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="128"
	 */
	private String business;
	/**
	 * @hibernate.property length="128"
	 */	
	private String receiver_email;
	/**
	 * @hibernate.property length="32"
	 */
	private String receiver_id;
	/**
	 * @hibernate.property length="128"
	 */	
	private String payer_email;
	/**
	 * @hibernate.property length="32"
	 */
	private String payer_id;
	/**
	 * @hibernate.property length="10"
	 */
	private String payer_status;
	/**
	 * @hibernate.property length="128"
	 */
	private String payment_date;
	/**
	 * @hibernate.property
	 */
	private float payment_fee;
	/**
	 * @hibernate.property
	 */	
	private float payment_gross;
	/**
	 * @hibernate.property length="10"
	 */	
	private String payment_status;
	/**
	 * @hibernate.property length="40"
	 */		
	private String payment_type;
	/**
	 * @hibernate.property
	 */		
	private float mc_gross;
	/**
	 * @hibernate.property
	 */		
	private float mc_fee;
	/**
	 * @hibernate.property length="3"
	 */		
	private String mc_currency;
	/**
	 * @hibernate.property length="128"
	 */	
	private String invoice;
	/**
	 * @hibernate.property length="128"
	 */		
	private String verify_sign;
	/**
	 * @hibernate.property
	 */		
	private float notify_version;
	/**
	 * @hibernate.property length="40"
	 */	
	private String txn_type;
	/**
	 * @hibernate.property length="64"
	 */	
	private String address_name;
	/**
	 * @hibernate.property length="254"
	 */	
	private String address_street;
	/**
	 * @hibernate.property length="120"
	 */	
	private String address_city;
	/**
	 * @hibernate.property length="120"
	 */	
	private String address_state;
	/**
	 * @hibernate.property length="10"
	 */	
	private String address_zip;
	/**
	 * @hibernate.property length="64"
	 */	
	private String address_country;
	/**
	 * @hibernate.property length="11"
	 */	
	private String address_status;
	/**
	 * @hibernate.property
	 */	
	private float shipping;
	/**
	 * @hibernate.property length="120"
	 */	
	private String transaction_subject;
	/**
	 * @hibernate.property length="40"
	 */	
	private String residence_country;
	
	/**
	 * @hibernate.many-to-one column="orderId" class="com.hnfealean.sport.model.shopping.Order"
	 */
	private Order order;
	/**
	 * @hibernate.property length="120"
	 */		
	private String pending_reason ;

	public String getPending_reason() {
		return pending_reason;
	}

	public void setPending_reason(String pending_reason) {
		this.pending_reason = pending_reason;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getReceiver_email() {
		return receiver_email;
	}

	public void setReceiver_email(String receiver_email) {
		this.receiver_email = receiver_email;
	}

	public String getReceiver_id() {
		return receiver_id;
	}

	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}

	public String getPayer_email() {
		return payer_email;
	}

	public void setPayer_email(String payer_email) {
		this.payer_email = payer_email;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}

	public String getPayer_status() {
		return payer_status;
	}

	public void setPayer_status(String payer_status) {
		this.payer_status = payer_status;
	}

	public String getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(String payment_date) {
		this.payment_date = payment_date;
	}

	public float getPayment_fee() {
		return payment_fee;
	}

	public void setPayment_fee(float payment_fee) {
		this.payment_fee = payment_fee;
	}

	public float getPayment_gross() {
		return payment_gross;
	}

	public void setPayment_gross(float payment_gross) {
		this.payment_gross = payment_gross;
	}

	public String getPayment_status() {
		return payment_status;
	}

	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}


	public float getMc_fee() {
		return mc_fee;
	}

	public void setMc_fee(float mc_fee) {
		this.mc_fee = mc_fee;
	}

	public String getMc_currency() {
		return mc_currency;
	}

	public void setMc_currency(String mc_currency) {
		this.mc_currency = mc_currency;
	}

	public String getInvoice() {
		return invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}

	public String getVerify_sign() {
		return verify_sign;
	}

	public void setVerify_sign(String verify_sign) {
		this.verify_sign = verify_sign;
	}


	public float getMc_gross() {
		return mc_gross;
	}

	public void setMc_gross(float mc_gross) {
		this.mc_gross = mc_gross;
	}

	public float getNotify_version() {
		return notify_version;
	}

	public void setNotify_version(float notify_version) {
		this.notify_version = notify_version;
	}

	public String getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(String txn_type) {
		this.txn_type = txn_type;
	}

	public String getAddress_name() {
		return address_name;
	}

	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}

	public String getAddress_street() {
		return address_street;
	}

	public void setAddress_street(String address_street) {
		this.address_street = address_street;
	}

	public String getAddress_city() {
		return address_city;
	}

	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}

	public String getAddress_state() {
		return address_state;
	}

	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}

	public String getAddress_zip() {
		return address_zip;
	}

	public void setAddress_zip(String address_zip) {
		this.address_zip = address_zip;
	}

	public String getAddress_country() {
		return address_country;
	}

	public void setAddress_country(String address_country) {
		this.address_country = address_country;
	}

	public String getAddress_status() {
		return address_status;
	}

	public void setAddress_status(String address_status) {
		this.address_status = address_status;
	}

	public float getShipping() {
		return shipping;
	}

	public void setShipping(float shipping) {
		this.shipping = shipping;
	}

	public String getTransaction_subject() {
		return transaction_subject;
	}

	public void setTransaction_subject(String transaction_subject) {
		this.transaction_subject = transaction_subject;
	}

	public String getResidence_country() {
		return residence_country;
	}

	public void setResidence_country(String residence_country) {
		this.residence_country = residence_country;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}


}
