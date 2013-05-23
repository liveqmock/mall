package com.hnfealean.sport.model.log;

import java.util.Date;
/**
 * @hibernate.class table="t_administrator_view_log"
 * @author Administrator
 *
 */
public class AdministratorViewLog {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property
	 */
	private int adminId;
	/**
	 * @hibernate.property length="20"
	 */
	private String adminName;
	/**
	 * @hibernate.property length="255"
	 */
	private String accessUrl;
	/**
	 * @hibernate.property 
	 */
	private Date  operatortime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public Date getOperatortime() {
		return operatortime;
	}

	public void setOperatortime(Date operatortime) {
		this.operatortime = operatortime;
	}
	
}
