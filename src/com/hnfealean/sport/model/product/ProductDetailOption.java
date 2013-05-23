package com.hnfealean.sport.model.product;

/**
 * @hibernate.class table="t_product_detail_option"
 * @author Administrator
 *
 */
public class ProductDetailOption {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="20" not-null="true"
	 */
	private String name;
	
	/**
	 * @hibernate.property length="300"
	 */
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
