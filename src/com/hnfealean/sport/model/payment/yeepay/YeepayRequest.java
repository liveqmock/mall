package com.hnfealean.sport.model.payment.yeepay;

import java.util.Date;

/**
 * @hibernate.class table="t_yeepay_request"
 * @author Administrator
 *
 */
public class YeepayRequest {
	public YeepayRequest() {
		super();
	}
	public YeepayRequest(String p0_Cmd, String p1_MerId, String p2_Order, String p3_Amt,
			String p4_Cur, String p5_Pid, String p6_Pcat, String p7_Pdesc, String p8_Url,
			String p9_saf, String pa_MP, String pd_FrpId,
			String pr_NeedResponse, String keyValue) {
		super();
		this.p0_Cmd = p0_Cmd;
		this.p1_MerId = p1_MerId;
		this.p2_Order = p2_Order;
		this.p3_Amt = p3_Amt;
		this.p4_Cur = p4_Cur;
		this.p5_Pid = p5_Pid;
		this.p6_Pcat = p6_Pcat;
		this.p7_Pdesc = p7_Pdesc;
		this.p8_Url = p8_Url;
		this.p9_SAF = p9_saf;
		this.pa_MP = pa_MP;
		this.pd_FrpId = pd_FrpId;
		this.pr_NeedResponse = pr_NeedResponse;
		this.keyValue = keyValue;
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String p0_Cmd;
	/**
	 * @hibernate.property
	 */
	private String p1_MerId;
	/**
	 * @hibernate.property
	 */
	private String p2_Order;
	/**
	 * @hibernate.property
	 */
	private String p3_Amt;
	/**
	 * @hibernate.property
	 */
	private String p4_Cur;
	/**
	 * @hibernate.property
	 */
	private String p5_Pid;
	/**
	 * @hibernate.property
	 */
	private String p6_Pcat;
	/**
	 * @hibernate.property
	 */
	private String p7_Pdesc;
	/**
	 * @hibernate.property
	 */
	private String p8_Url;
	/**
	 * @hibernate.property
	 */
	private String p9_SAF;
	/**
	 * @hibernate.property
	 */
	private String pa_MP;
	/**
	 * @hibernate.property
	 */
	private String pd_FrpId;
	/**
	 * @hibernate.property
	 */
	private String pr_NeedResponse;
	/**
	 * @hibernate.property
	 */
	private String keyValue;
	/**
	 * @hibernate.property
	 */
	private String md5hmac;
	/**
	 * @hibernate.property
	 */
	public Date date = new Date();
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMd5hmac() {
		return md5hmac;
	}
	public void setMd5hmac(String md5hmac) {
		this.md5hmac = md5hmac;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getP0_Cmd() {
		return p0_Cmd;
	}
	public void setP0_Cmd(String cmd) {
		p0_Cmd = cmd;
	}
	public String getP1_MerId() {
		return p1_MerId;
	}
	public void setP1_MerId(String merId) {
		p1_MerId = merId;
	}
	public String getP2_Order() {
		return p2_Order;
	}
	public void setP2_Order(String order) {
		p2_Order = order;
	}
	public String getP3_Amt() {
		return p3_Amt;
	}
	public void setP3_Amt(String amt) {
		p3_Amt = amt;
	}
	public String getP4_Cur() {
		return p4_Cur;
	}
	public void setP4_Cur(String cur) {
		p4_Cur = cur;
	}
	public String getP5_Pid() {
		return p5_Pid;
	}
	public void setP5_Pid(String pid) {
		p5_Pid = pid;
	}
	public String getP6_Pcat() {
		return p6_Pcat;
	}
	public void setP6_Pcat(String pcat) {
		p6_Pcat = pcat;
	}
	public String getP7_Pdesc() {
		return p7_Pdesc;
	}
	public void setP7_Pdesc(String pdesc) {
		p7_Pdesc = pdesc;
	}
	public String getP8_Url() {
		return p8_Url;
	}
	public void setP8_Url(String url) {
		p8_Url = url;
	}
	public String getP9_SAF() {
		return p9_SAF;
	}
	public void setP9_SAF(String p9_saf) {
		p9_SAF = p9_saf;
	}
	public String getPa_MP() {
		return pa_MP;
	}
	public void setPa_MP(String pa_MP) {
		this.pa_MP = pa_MP;
	}
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public String getPr_NeedResponse() {
		return pr_NeedResponse;
	}
	public void setPr_NeedResponse(String pr_NeedResponse) {
		this.pr_NeedResponse = pr_NeedResponse;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
}
