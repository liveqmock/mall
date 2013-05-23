package com.hnfealean.sport.model.product;

import java.util.List;
import java.util.Set;

/**
 * @hibernate.class table="t_product_detail_attribute"
 * @author Administrator
 *
 */
public class ProductDetailAttribute {
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="20" not-null="true"
	 */
	private String name;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="false" cascade="delete-orphan"
	 *  @hibernate.key column="detail_attribute_id"
	 *  @hibernate.one-to-many class="com.hnfealean.sport.model.product.ProductDetailOption"
	 */
	private Set<ProductDetailOption> options;
	


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

	public Set<ProductDetailOption> getOptions() {
		return options;
	}

	public void setOptions(Set<ProductDetailOption> options) {
		this.options = options;
	}

	
}
