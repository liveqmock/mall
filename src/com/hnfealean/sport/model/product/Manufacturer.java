package com.hnfealean.sport.model.product;

/**
 * @hibernate.class table="t_manufacturer"
 * @author Administrator
 *
 */
public class Manufacturer {

	/**
	 * @hibernate.id 
	 * 		generator-class="native"
	 */
	private int id;

	/**
	 * @hibernate.property length="30" not-null="true"
	 * 		厂商名称
	 */
	private String name;

	/** 
	 * @hibernate.property length="100"
	 * 		厂商图片 
	 */  
	private String imageUrl;

	/**
	 * @hibernate.many-to-one column="productId" class="com.hnfealean.sport.model.product.Product"
	 */
	private Product product;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
