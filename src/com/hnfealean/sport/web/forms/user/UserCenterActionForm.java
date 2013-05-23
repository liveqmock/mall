package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

import com.hnfealean.sport.enums.Gender;

public class UserCenterActionForm extends ActionForm {
	private int id;
	private String password;
	private String oldPassword;
	private int conatctInfoId;
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
	private String validateCode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public String getPassword() {
		return password;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCountry() {
		return country;
	}

	public int getConatctInfoId() {
		return conatctInfoId;
	}

	public void setConatctInfoId(int conatctInfoId) {
		this.conatctInfoId = conatctInfoId;
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

	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
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

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
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

}
