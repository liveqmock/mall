package com.hnfealean.sport.model.product;

/**
 * @hibernate.class table="t_tag"
 * @author Administrator
 *
 */
public class Tag {
	/*
	public static String PRODUCT="product";
	public static String NEWS="news";
	public static String BLOG="blog";*/
	public Tag(){
		super();
	}
	public Tag(String name, int targetId, int type) {
		super();
		this.name = name;
		this.targetId = targetId;
		this.type = type;
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 */
	private String name;
	/**
	 * @hibernate.property
	 */
	private int targetId;
	/**
	 * @hibernate.property
	 */	
	private int type;
	
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	


}
