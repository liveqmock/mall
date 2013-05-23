package com.hnfealean.sport.model.payment.yeepay;
/**
 * @hibernate.class table="t_yeepay_response"
 * @author Administrator
 *
 */
public class YeepayResponse {
	public YeepayResponse(String hmac, String p1_MerId, String r0_Cmd, String r1_Code,
			String r2_TrxId, String r3_Amt, String r4_Cur, String r5_Pid, String r6_Order,
			String r7_Uid, String r8_MP, String r9_BType, String keyValue) {
		super();
		this.hmac = hmac;
		this.p1_MerId = p1_MerId;
		this.r0_Cmd = r0_Cmd;
		this.r1_Code = r1_Code;
		this.r2_TrxId = r2_TrxId;
		this.r3_Amt = r3_Amt;
		this.r4_Cur = r4_Cur;
		this.r5_Pid = r5_Pid;
		this.r6_Order = r6_Order;
		this.r7_Uid = r7_Uid;
		this.r8_MP = r8_MP;
		this.r9_BType = r9_BType;
		this.keyValue = keyValue;
	}
	public YeepayResponse() {
		super();
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private String hmac;
	/**
	 * @hibernate.property
	 */
	private String p1_MerId;
	/**
	 * @hibernate.property
	 */
	private String r0_Cmd;
	/**
	 * @hibernate.property
	 */
	private String r1_Code;
	/**
	 * @hibernate.property
	 */
	private String r2_TrxId;
	/**
	 * @hibernate.property
	 */
	private String r3_Amt;
	/**
	 * @hibernate.property
	 */
	private String r4_Cur;
	/**
	 * @hibernate.property
	 */
	private String r5_Pid;
	/**
	 * @hibernate.property
	 */
	private String r6_Order;
	/**
	 * @hibernate.property
	 */
	private String r7_Uid;
	/**
	 * @hibernate.property
	 */
	private String r8_MP;
	/**
	 * @hibernate.property
	 */
	private String r9_BType;
	/**
	 * @hibernate.property
	 */
	private String keyValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
