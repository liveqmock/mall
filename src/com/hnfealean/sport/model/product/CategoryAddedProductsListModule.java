package com.hnfealean.sport.model.product;


/**
 * @hibernate.class table="t_addproductslistmodule"
 * @author Administrator
 *
 */
public class CategoryAddedProductsListModule {


	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="10"
	 */
	private String name;
	
	/**
	 * @hibernate.property
	 */
	private int categoryId;
	
	/**
		@hibernate.property	length="256" 
	 */
	private String productIds;


	public String getProductIds() {
		return productIds;
	}

	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
