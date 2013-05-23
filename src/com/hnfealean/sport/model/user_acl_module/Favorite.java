package com.hnfealean.sport.model.user_acl_module;

import java.util.Date;
import java.util.Set;
/**
 * @hibernate.class table="t_favorite"
 * @author Administrator
 *
 */
public class Favorite {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private Date date;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="true" cascade="delete" inverse="true"
	 * @hibernate.key column="itemId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.user_acl_module.FavoriteItem"
	 */
	private Set<FavoriteItem> items;
	
	/**
	 * @hibernate.many-to-one
	 */
	private User user;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<FavoriteItem> getItems() {
		return items;
	}

	public void setItems(Set<FavoriteItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
