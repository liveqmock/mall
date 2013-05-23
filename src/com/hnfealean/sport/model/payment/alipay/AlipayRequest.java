package com.hnfealean.sport.model.payment.alipay;
/**
 * @hibernate.class table="t_alipay_request"
 * @author Administrator
 *
 */
public class AlipayRequest {

	public AlipayRequest(String alipaysubmit, String receive_mobile,
			String logistics_fee, String notify_url, String payment_type,
			String service, String _input_charset, String logistics_type,
			String price, String out_trade_no, String receive_address,
			String logistics_payment, String subject, String receive_zip,
			String quantity, String receive_name, String body,
			String return_url, String show_url, String receive_phone,
			String sign, String sign_type) {
		super();
		this.alipaysubmit = alipaysubmit;
		this.receive_mobile = receive_mobile;
		this.logistics_fee = logistics_fee;
		this.notify_url = notify_url;
		this.payment_type = payment_type;
		this.service = service;
		this._input_charset = _input_charset;
		this.logistics_type = logistics_type;
		this.price = price;
		this.out_trade_no = out_trade_no;
		this.receive_address = receive_address;
		this.logistics_payment = logistics_payment;
		this.subject = subject;
		this.receive_zip = receive_zip;
		this.quantity = quantity;
		this.receive_name = receive_name;
		this.body = body;
		this.return_url = return_url;
		this.show_url = show_url;
		this.receive_phone = receive_phone;
		this.sign = sign;
		this.sign_type = sign_type;
	}
	public AlipayRequest() {
		super();
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String alipaysubmit;
	/**
	 * @hibernate.property
	 */
	private String receive_mobile;
	/**
	 * @hibernate.property
	 */
	private String logistics_fee;
	/**
	 * @hibernate.property
	 */
	private String notify_url;
	/**
	 * @hibernate.property
	 */
	private String payment_type;
	/**
	 * @hibernate.property
	 */
	private String service;
	/**
	 * @hibernate.property
	 */
	private String _input_charset;
	/**
	 * @hibernate.property
	 */
	private String logistics_type;
	/**
	 * @hibernate.property
	 */
	private String price;
	/**
	 * @hibernate.property
	 */
	private String out_trade_no;
	/**
	 * @hibernate.property
	 */
	private String receive_address;
	/**
	 * @hibernate.property
	 */
	private String logistics_payment;
	/**
	 * @hibernate.property
	 */
	private String subject;
	/**
	 * @hibernate.property
	 */
	private String receive_zip;
	/**
	 * @hibernate.property
	 */
	private String quantity;
	/**
	 * @hibernate.property
	 */
	private String receive_name;
	/**
	 * @hibernate.property
	 */
	private String body;
	/**
	 * @hibernate.property
	 */
	private String return_url;
	/**
	 * @hibernate.property
	 */
	private String show_url;
	/**
	 * @hibernate.property
	 */
	private String receive_phone;
	/**
	 * @hibernate.property
	 */
	private String sign;
	/**
	 * @hibernate.property
	 */
	private String sign_type;
	
	private String buyer_email;
	private String key;
	private String discount;
	private String logistics_fee_1;
	private String logistics_type_1;
	private String logistics_payment_1;
	private String logistics_fee_2;
	private String logistics_type_2;
	private String logistics_payment_2;
	private String partner;
	private String seller_email;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getLogistics_payment_2() {
		return logistics_payment_2;
	}
	public void setLogistics_payment_2(String logistics_payment_2) {
		this.logistics_payment_2 = logistics_payment_2;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getLogistics_fee_1() {
		return logistics_fee_1;
	}
	public void setLogistics_fee_1(String logistics_fee_1) {
		this.logistics_fee_1 = logistics_fee_1;
	}
	public String getLogistics_type_1() {
		return logistics_type_1;
	}
	public void setLogistics_type_1(String logistics_type_1) {
		this.logistics_type_1 = logistics_type_1;
	}
	public String getLogistics_payment_1() {
		return logistics_payment_1;
	}
	public void setLogistics_payment_1(String logistics_payment_1) {
		this.logistics_payment_1 = logistics_payment_1;
	}
	public String getLogistics_fee_2() {
		return logistics_fee_2;
	}
	public void setLogistics_fee_2(String logistics_fee_2) {
		this.logistics_fee_2 = logistics_fee_2;
	}
	public String getLogistics_type_2() {
		return logistics_type_2;
	}
	public void setLogistics_type_2(String logistics_type_2) {
		this.logistics_type_2 = logistics_type_2;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlipaysubmit() {
		return alipaysubmit;
	}
	public void setAlipaysubmit(String alipaysubmit) {
		this.alipaysubmit = alipaysubmit;
	}
	public String getReceive_mobile() {
		return receive_mobile;
	}
	public void setReceive_mobile(String receive_mobile) {
		this.receive_mobile = receive_mobile;
	}
	public String getLogistics_fee() {
		return logistics_fee;
	}
	public void setLogistics_fee(String logistics_fee) {
		this.logistics_fee = logistics_fee;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String get_input_charset() {
		return _input_charset;
	}
	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}
	public String getLogistics_type() {
		return logistics_type;
	}
	public void setLogistics_type(String logistics_type) {
		this.logistics_type = logistics_type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getReceive_address() {
		return receive_address;
	}
	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}
	public String getLogistics_payment() {
		return logistics_payment;
	}
	public void setLogistics_payment(String logistics_payment) {
		this.logistics_payment = logistics_payment;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getReceive_zip() {
		return receive_zip;
	}
	public void setReceive_zip(String receive_zip) {
		this.receive_zip = receive_zip;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getShow_url() {
		return show_url;
	}
	public void setShow_url(String show_url) {
		this.show_url = show_url;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
}
