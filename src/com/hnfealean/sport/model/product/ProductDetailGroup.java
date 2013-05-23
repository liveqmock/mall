package com.hnfealean.sport.model.product;

import java.util.List;
import java.util.Set;

/**
 * @hibernate.class table="t_product_detail_group"
 * @author Administrator
 *
 */
public class ProductDetailGroup {
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property 
	 */
	private String name;
	
	/**
	 * @hibernate.set order-by="id asc" inverse="false" lazy="false" cascade="delete-orphan"
	 * @hibernate.key column="detail_group_id"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.ProductDetailAttribute"
	 */
	private Set<ProductDetailAttribute> detailAttributes;
	/**
	 * @hibernate.property length="256"
	 */
	private String post;
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
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

	public Set<ProductDetailAttribute> getDetailAttributes() {
		return detailAttributes;
	}

	public void setDetailAttributes(Set<ProductDetailAttribute> detailAttributes) {
		this.detailAttributes = detailAttributes;
	}

}
