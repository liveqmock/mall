package com.hnfealean.sport.web.forms.payment;

import org.apache.struts.action.ActionForm;

public class YeepayForm extends ActionForm {

	private int id;
	private String orderId;
	private String pd_FrpId;
	private String frpId;
	private String hmac;
	private String p1_MerId;
	private String r0_Cmd;
	private String r1_Code;
	private String r2_TrxId;
	private String r3_Amt;
	private String r4_Cur;
	private String r5_Pid;
	private String r6_Order;
	private String r7_Uid;
	private String r8_MP;
	private String r9_BType;
	private String keyValue;
	public String getFrpId() {
		return frpId;
	}
	public void setFrpId(String frpId) {
		this.frpId = frpId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPd_FrpId() {
		return pd_FrpId;
	}
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}
	public String getHmac() {
		return hmac;
	}
	public void setHmac(String hmac) {
		this.hmac = hmac;
	}
	public String getP1_MerId() {
		return p1_MerId;
	}
	public void setP1_MerId(String merId) {
		p1_MerId = merId;
	}
	public String getR0_Cmd() {
		return r0_Cmd;
	}
	public void setR0_Cmd(String cmd) {
		r0_Cmd = cmd;
	}
	public String getR1_Code() {
		return r1_Code;
	}
	public void setR1_Code(String code) {
		r1_Code = code;
	}
	public String getR2_TrxId() {
		return r2_TrxId;
	}
	public void setR2_TrxId(String trxId) {
		r2_TrxId = trxId;
	}
	public String getR3_Amt() {
		return r3_Amt;
	}
	public void setR3_Amt(String amt) {
		r3_Amt = amt;
	}
	public String getR4_Cur() {
		return r4_Cur;
	}
	public void setR4_Cur(String cur) {
		r4_Cur = cur;
	}
	public String getR5_Pid() {
		return r5_Pid;
	}
	public void setR5_Pid(String pid) {
		r5_Pid = pid;
	}
	public String getR6_Order() {
		return r6_Order;
	}
	public void setR6_Order(String order) {
		r6_Order = order;
	}
	public String getR7_Uid() {
		return r7_Uid;
	}
	public void setR7_Uid(String uid) {
		r7_Uid = uid;
	}
	public String getR8_MP() {
		return r8_MP;
	}
	public void setR8_MP(String r8_mp) {
		r8_MP = r8_mp;
	}
	public String getR9_BType() {
		return r9_BType;
	}
	public void setR9_BType(String type) {
		r9_BType = type;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

}
