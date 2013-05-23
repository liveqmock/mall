package com.hnfealean.sport.model.user_acl_module;
/**
 * @hibernate.class table="t_purse"
 * @author Administrator
 *
 */
public class Purse {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private float overage;
	
	/**
	 * @hibernate.many-to-one
	 */
	private User user;

	public Purse(int purseId) {

		this.id=purseId;
	}
	
	public Purse() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getOverage() {
		return overage;
	}

	public void setOverage(float overage) {
		this.overage = overage;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
