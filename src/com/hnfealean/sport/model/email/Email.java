package com.hnfealean.sport.model.email;
/**
 * @hibernate.class table="t_email"
 * @author Administrator
 *
 */
public class Email {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="50" not-null="true"
	 */
	private String address;
	/**
	 * @hibernate.property
	 */
	private boolean isDefault;
	
	/**
	 * @hibernate.property length="50" not-null="true"
	 */	
	private String smtpHost;
	/**
	 * @hibernate.property not-null="true" length="5"
	 */
	private String smtpPort;
	/**
	 * @hibernate.property length="50" not-null="true"
	 */	
	private String username;
	/**
	 * @hibernate.property length="50" not-null="true"
	 */	
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isDefault() {
		return isDefault;
	}
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	
	
}
