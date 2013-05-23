package com.hnfealean.sport.model.log;

import java.util.Date;
/**
 * @hibernate.class table="t_user_view_log"
 * @author Administrator
 *
 */
public class UserViewLog {

	public UserViewLog() {
		super();
	}

	public UserViewLog( String ip, String zone, Date inTime,
			String inUrl, String referer, int userId, String userEmail) {
		super();
		this.ip = ip;
		this.zone = zone;
		this.inTime = inTime;
		this.inUrl = inUrl;
		this.referer = referer;
		this.userId = userId;
		this.userEmail = userEmail;
	}

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="30"
	 */
	private String ip;
	/**
	 * @hibernate.property length="40"
	 */
	private String zone;
	/**
	 * @hibernate.property
	 */
	private Date inTime;
	/**
	 * @hibernate.property length="255"
	 */
	private String inUrl;
	/**
	 * @hibernate.property length="255"
	 */
	private String referer;
	/**
	 * @hibernate.property 
	 */
	private int userId;
	/**
	 * @hibernate.property length="50"
	 */
	private String userEmail;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getInUrl() {
		return inUrl;
	}

	public void setInUrl(String inUrl) {
		if(inUrl!=null&&inUrl.length()>255){
			inUrl = inUrl.substring(0,255);
			return;
		}
		this.inUrl = inUrl;
	}

	public String getReferer() {

		return referer;
	}

	public void setReferer(String referer) {
		if(referer!=null&&referer.length()>255){
			referer = referer.substring(0,255);
			return;
		}
		this.referer = referer;
	}
	
	
}
