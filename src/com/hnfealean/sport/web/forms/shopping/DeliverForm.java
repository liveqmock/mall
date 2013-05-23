package com.hnfealean.sport.web.forms.shopping;

import org.apache.struts.action.ActionForm;

import com.hnfealean.sport.enums.Gender;

public class DeliverForm  extends ActionForm  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5779289733622038462L;


	private int deliverInfoId;
	private String name;
	
	private int country;
	private int state;
	private int city;
	private int county;	
	private String street_address;
	
	private String gender = Gender.MAN.toString();
	
	private String email;
	
	private String postCode;
	
	private String mobile;
	
	private String phone;
	
	private int isUser=1;

	public int getDeliverInfoId() {
		return deliverInfoId;
	}

	public void setDeliverInfoId(int deliverInfoId) {
		this.deliverInfoId = deliverInfoId;
	}

	public String getName() {
		return name;
	}

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCounty() {
		return county;
	}

	public void setCounty(int county) {
		this.county = county;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String streetAddress) {
		street_address = streetAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getIsUser() {
		return isUser;
	}

	public void setIsUser(int isUser) {
		this.isUser = isUser;
	}


	

}
