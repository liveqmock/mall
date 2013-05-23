package com.hnfealean.sport.model.payment.alipay;
/**
 * @hibernate.class table="t_alipay_configuration"
 * @author Administrator
 *
 */
public class AlipayConfiguration {

	public static String SECUREDTRANSACTIONS="create_partner_trade_by_user";//纯担保交易
	public static String DIRECTTRANSACTIONS="create_partner_trade_by_user";//即时到帐
	public static String BOTHSECUREDANDDIRECT="trade_create_by_user";//双功能
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property not-null="true" length="50"
	 */
	private String paymentType=DIRECTTRANSACTIONS;
	/**
	 * @hibernate.property not-null="true" length="16"
	 */
	private String partner="";
	/**
	 * @hibernate.property
	 */
	private String partner_description;
	/**
	 * @hibernate.property not-null="true" length="32" column="secure_key"
	 */
	private String key="11111111111111111111111111111111";
	/**
	 * @hibernate.property
	 */
	private String key_description;
	/**
	 * @hibernate.property not-null="true" length="100"
	 */
	private String seller_email="";
	/**
	 * @hibernate.property
	 */
	private String seller_email_description;
	/**
	 * @hibernate.property not-null="true"
	 */
	private String notify_url="";
	/**
	 * @hibernate.property
	 */
	private String notify_url_description;
	/**
	 * @hibernate.property
	 */
	private String return_url="";
	/**
	 * @hibernate.property
	 */
	private String return_url_description;
	/**
	 * @hibernate.property
	 */
	private String show_url="";
	/**
	 * @hibernate.property
	 */
	private String show_url_description;
	/**
	 * @hibernate.property not-null="true"
	 */
	private String mainname="";
	/**
	 * @hibernate.property
	 */
	private String mainname_description;
	/**
	 * @hibernate.property not-null="true" length="16"
	 */
	private String input_charset = "UTF-8";
	/**
	 * @hibernate.property
	 */
	private String input_charset_description ;
	
	
	// 签名方式 不需修改
	/**
	 * @hibernate.property not-null="true" length="16"
	 */
	private String sign_type = "MD5";
	/**
	 * @hibernate.property
	 */
	private String sign_type_description;
	
	//访问模式,根据自己的服务器是否支持ssl访问，若支持请选择https；若不支持请选择http
	private String transport = "http";
	/**
	 * @hibernate.property
	 */
	private String transport_description;
	/**
	 * @hibernate.property
	 */
	private String alipaysubmit;
	/**
	 * @hibernate.property
	 */
	private String alipaysubmit_description="";
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
	public String getPartner_description() {
		return partner_description;
	}
	public void setPartner_description(String partner_description) {
		this.partner_description = partner_description;
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
	public String getSeller_email_description() {
		return seller_email_description;
	}
	public void setSeller_email_description(String seller_email_description) {
		this.seller_email_description = seller_email_description;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getNotify_url_description() {
		return notify_url_description;
	}
	public void setNotify_url_description(String notify_url_description) {
		this.notify_url_description = notify_url_description;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getReturn_url_description() {
		return return_url_description;
	}
	public void setReturn_url_description(String return_url_description) {
		this.return_url_description = return_url_description;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getShow_url_description() {
		return show_url_description;
	}
	public void setShow_url_description(String show_url_description) {
		this.show_url_description = show_url_description;
	}
	public String getMainname() {
		return mainname;
	}
	public void setMainname(String mainname) {
		this.mainname = mainname;
	}
	public String getMainname_description() {
		return mainname_description;
	}
	public void setMainname_description(String mainname_description) {
		this.mainname_description = mainname_description;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getInput_charset_description() {
		return input_charset_description;
	}
	public void setInput_charset_description(String input_charset_description) {
		this.input_charset_description = input_charset_description;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getSign_type_description() {
		return sign_type_description;
	}
	public void setSign_type_description(String sign_type_description) {
		this.sign_type_description = sign_type_description;
	}
	public String getTransport() {
		return transport;
	}
	public void setTransport(String transport) {
		this.transport = transport;
	}
	public String getTransport_description() {
		return transport_description;
	}
	public void setTransport_description(String transport_description) {
		this.transport_description = transport_description;
	}
	public String getAlipaysubmit() {
		return alipaysubmit;
	}
	public void setAlipaysubmit(String alipaysubmit) {
		this.alipaysubmit = alipaysubmit;
	}
	public String getAlipaysubmit_description() {
		return alipaysubmit_description;
	}
	public void setAlipaysubmit_description(String alipaysubmit_description) {
		this.alipaysubmit_description = alipaysubmit_description;
	}

}