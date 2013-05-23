package com.hnfealean.sport.web.forms.payment;

import org.apache.struts.action.ActionForm;

public class PaypalForm extends ActionForm {
	private int id;
	private int[] ids;
	private String business;
	private String receiver_email;
	private String receiver_id;
	private String payer_email;
	private String payer_id;
	private String payer_status;
	private String payment_date;
	private float payment_fee;
	private float payment_gross;
	private String payment_status;
	private String payment_type;
	private float mc_gross;
	private float mc_fee;
	private String mc_currency;
	private String invoice;
	private String verify_sign;
	private float notify_version;
	private String txn_id;
	private String txn_type;
	private String address_name;
	private String address_street;
	private String address_city;
	private String address_state;
	private String address_zip;
	private String address_country;
	private String address_status;
	private float shipping;
	private String transaction_subject;
	private String residence_country;
	private String orderId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
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
	public float getMc_gross() {
		return mc_gross;
	}
	public void setMc_gross(float mc_gross) {
		this.mc_gross = mc_gross;
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
	public float getNotify_version() {
		return notify_version;
	}
	public void setNotify_version(float notify_version) {
		this.notify_version = notify_version;
	}
	public String getTxn_id() {
		return txn_id;
	}
	public void setTxn_id(String txn_id) {
		this.txn_id = txn_id;
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


}
