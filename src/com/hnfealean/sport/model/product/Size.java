package com.hnfealean.sport.model.product;

import java.io.Serializable;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;

/**
 * @hibernate.class table="t_size"
 * @author Administrator
 *
 */
@Searchable(root = false)
public class Size  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 889953842514591875L;

	/**
	 * @hibernate.id 
	 * 		generator-class="native"
	 */
	@SearchableId
	private int id;
	
	/**
	 * @hibernate.property not-null="true" length="50"
	 */
	private String name;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
