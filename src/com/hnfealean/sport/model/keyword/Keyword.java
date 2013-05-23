package com.hnfealean.sport.model.keyword;

/**
 * @hibernate.class table="t_keyword"
 * @author Administrator
 *
 */
public class Keyword {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="20"
	 */
	private String value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
