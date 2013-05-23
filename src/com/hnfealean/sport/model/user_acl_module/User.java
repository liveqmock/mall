package com.hnfealean.sport.model.user_acl_module;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.hnfealean.sport.model.shopping.Order;

/**
 * @hibernate.class table="t_user"  lazy="true"
 * @author Administrator
 *
 */
public class User implements Serializable{
	public User(){
		super();
	}
	
	public User(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
	}

	public User(int id, String username) {
		super();
		this.id = id;
		this.username = username;
	}



	/**
	 * @hibernate.id 
	 * 		generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property 
	 * 		not-null="true" length=16
	 */
	private String username;
	/**
	 * @hibernate.property unique="true" length="50"
	 */
	private String email;
	/**
	 * @hibernate.property
	 * 		not-null="true" length=32
	 */
	private String password;
	
	/**
	 * @hibernate.property
	 * 		not-null="true"
	 */	
	private Boolean visible= true;
	

	/**
	 * @hibernate.property  lazy="true"
	 * 	
	 */
	private Date createDate;
	
	/**
	 * @hibernate.property  lazy="true"
	 * 
	 */
	private Date expireDate;
	
	/**
	 * 
	 *@hibernate.many-to-one cascade="all" column="contactInfoId" not-null="true" class="com.hnfealean.sport.model.user_acl_module.ContactInfo"
	 */
	private	ContactInfo contactInfo;
	


	/**
	 * 用户的购物历史
	 * 	明显：用户--购物历史
	 * 			1--*
	 * 一对多：在一的一端集合上使用key，在多的一方的表中增加一个外键指向一的一端
	 * 		   在多的一端使用<many-to-one>
	 * 			inverse属性设置之后，只能从一的一端维护关联关系
	 * @hibernate.set order-by="id asc" inverse="true" lazy="true"
	 * @hibernate.key column="userId" 
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.shopping.Order"
	 */
	private Set<Order> orders;



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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



	public Boolean getVisible() {
		return visible;
	}



	public void setVisible(Boolean visible) {
		this.visible = visible;
	}



	public Date getCreateDate() {
		return createDate;
	}



	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public Date getExpireDate() {
		return expireDate;
	}



	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}



	public ContactInfo getContactInfo() {
		return contactInfo;
	}



	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}



	public Set<Order> getOrders() {
		return orders;
	}



	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}


}
