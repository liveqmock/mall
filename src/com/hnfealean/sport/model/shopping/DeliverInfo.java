package com.hnfealean.sport.model.shopping;

import com.hnfealean.sport.enums.Gender;
/**
 * @hibernate.class table="t_deliverinfo"
 * @author Administrator
 *
 */
public class DeliverInfo {

	/**
	 * 
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	/**
	 * 名字
	 * @hibernate.property
	 * 	
	 */
	private String name;		
	/**
	 * 街道地址
	 * @hibernate.property
	 * 	
	 */
	private String street_address;
	/**
	 * 国家
	 * @hibernate.property
	 * 
	 **/ 
	private String country;
	/**
	 * 州省
	 * @hibernate.property
	 */
	private String state;
	/**
	 * 市
 	 *  @hibernate.property
	 */
	private String city;
	/**
	 * 区县
	 * @hibernate.property
	 */
	private String county;

	
	/**
	 * 邮编	 
	 *  @hibernate.property
	 * 		not-null="true"
	 */
	private String postCode;
	

	/**
	 * 邮箱
 	 *  @hibernate.property
	 */
	private String email;
	
	/**
	 * 座机
	 *  @hibernate.property
	 * 		not-null="true" length="20"
	 */
	private String phone;
	
	/**
	 * 手机
	 *  @hibernate.property
	 * 		not-null="true" length="11"
	 */
	private String mobile;
	/**
	 * @hibernate.property length="20"
	 */
	private String gender = Gender.BOTH.toString();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStreet_address() {
		return street_address;
	}
	public void setStreet_address(String streetAddress) {
		street_address = streetAddress;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}

	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
