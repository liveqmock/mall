package com.hnfealean.sport.web.forms.payment;

import org.apache.struts.action.ActionForm;

public class AlipayConfigurationForm extends ActionForm {

	private int id;
	
	private String paymentType;
	private String partner;
	private String key;
	private String key_description;
	private String seller_email;
	private String return_url;
	private String notify_url;
	private String show_url;
	private String mainname;
	private String input_charset;
	private String sign_type;
	private String transport;
	private String alipaysubmit;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey_description() {
		return key_description;
	}
	public void setKey_description(String key_description) {
		this.key_description = key_description;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getMainname() {
		return mainname;
	}
	public void setMainname(String mainname) {
		this.mainname = mainname;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getAlipaysubmit() {
		return alipaysubmit;
	}
	public void setAlipaysubmit(String alipaysubmit) {
		this.alipaysubmit = alipaysubmit;
	}
}
