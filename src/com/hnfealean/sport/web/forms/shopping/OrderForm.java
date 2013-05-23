package com.hnfealean.sport.web.forms.shopping;

import org.apache.struts.action.ActionForm;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.enums.Gender;

public class OrderForm extends ActionForm {

	private int userId;
	private String orderId;
	
	private String orderState=ConstantString.ORDER_WAITCONFIRM;
	
	private String userName;
	
	private String userEmail;
	
	private String deliverName;
	
	private int deliverInfoId;
	
	private int productOptionId;

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
	
	private String date;
	private int paymentMethod;
	
	private int deliverType;
	
	private int orderItemId;
	
	private int amount;
	
	private int productId;
	
	private int attributeOptionId;

	public int getProductOptionId() {
		return productOptionId;
	}

	public void setProductOptionId(int productOptionId) {
		this.productOptionId = productOptionId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getAttributeOptionId() {
		return attributeOptionId;
	}

	public void setAttributeOptionId(int attributeOptionId) {
		this.attributeOptionId = attributeOptionId;
	}



	private float deliverFee=0.0f;
	
	private float payAbleFee;
	public float getPayAbleFee() {
		return payAbleFee;
	}

	public void setPayAbleFee(float payAbleFee) {
		this.payAbleFee = payAbleFee;
	}

	public float getDeliverFee() {
		return deliverFee;
	}

	public void setDeliverFee(float deliverFee) {
		this.deliverFee = deliverFee;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public int getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(int deliverType) {
		this.deliverType = deliverType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDeliverInfoId() {
		return deliverInfoId;
	}

	public void setDeliverInfoId(int deliverInfoId) {
		this.deliverInfoId = deliverInfoId;
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

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail =userEmail;
	
		
	}

	public String getDeliverName() {
		return deliverName;
	}

	public void setDeliverName(String deliverName) {
		this.deliverName = deliverName;
	}

//	public String getDate() {
//		return date;
//	}
//
//	public void setDate(String date) {
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = new Date(date);
//		String s = format.format(d);
//		this.date =date;
//	/*	if(date==null) {
//			this.date=null;
//			return;
//		}
//		boolean ok = Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", date);
//		if(ok){
//			
//		}else{
//			this.date=null;
//			return;
//		}
//		try {
//			this.date =(new SimpleDateFormat("yyyy-MM-dd")).format(new Date(date));
//		} catch (Exception e) {
//			this.date =null;
//		}*/
//		
//	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
