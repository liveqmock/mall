package com.hnfealean.sport.model.product;

/**
 * @hibernate.class table="t_productattribute"
 * @author Administrator
 *
 */
public class ProductAttribute {
	public ProductAttribute(int id) {
		super();
		this.id = id;
	}

	public ProductAttribute() {
		super();
	}

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="10" unique="true"
	 */
	private String name;
	


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
