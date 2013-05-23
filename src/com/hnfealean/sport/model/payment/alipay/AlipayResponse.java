package com.hnfealean.sport.model.payment.alipay;
/**
 * @hibernate.class table="t_alipay_response"
 * @author Administrator
 *
 */
public class AlipayResponse {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String trade_no;	//支付宝交易号
	/**
	 * @hibernate.property
	 */
	private String out_trade_no;//获取订单号
	/**
	 * @hibernate.property
	 */
	private String price; //获取总金额
	/**
	 * @hibernate.property
	 */
	private String subject;//商品名称、订单名称
	/**
	 * @hibernate.property
	 */
	private String body;//商品描述、订单备注、描述
	/**
	 * @hibernate.property
	 */
	private String buyer_email;//买家支付宝账号
	/**
	 * @hibernate.property
	 */
	private String receive_name;//收货人姓名
	/**
	 * @hibernate.property
	 */
	private String receive_address;//收货人地址
	/**
	 * @hibernate.property
	 */
	private String receive_zip;//收货人邮编
	/**
	 * @hibernate.property
	 */
	private String receive_phone;//收货人电话
	/**
	 * @hibernate.property
	 */
	private String receive_mobile;//收货人手机
	/**
	 * @hibernate.property
	 */
	private String trade_status;//交易状态
	/**
	 * @hibernate.property
	 */
	private String notify_id;//通知ID
	/**
	 * @hibernate.property
	 */
	private String sign;//参数签名
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getReceive_name() {
		return receive_name;
	}
	public void setReceive_name(String receive_name) {
		this.receive_name = receive_name;
	}
	public String getReceive_address() {
		return receive_address;
	}
	public void setReceive_address(String receive_address) {
		this.receive_address = receive_address;
	}
	public String getReceive_zip() {
		return receive_zip;
	}
	public void setReceive_zip(String receive_zip) {
		this.receive_zip = receive_zip;
	}
	public String getReceive_phone() {
		return receive_phone;
	}
	public void setReceive_phone(String receive_phone) {
		this.receive_phone = receive_phone;
	}
	public String getReceive_mobile() {
		return receive_mobile;
	}
	public void setReceive_mobile(String receive_mobile) {
		this.receive_mobile = receive_mobile;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}
