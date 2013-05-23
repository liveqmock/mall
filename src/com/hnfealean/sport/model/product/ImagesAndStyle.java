package com.hnfealean.sport.model.product;

import java.io.Serializable;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.SearchableReference;
import org.compass.annotations.Store;

/**
 * @hibernate.class table="t_imagesandstyles"
 * @author Administrator
 *
 */
@Searchable
public class ImagesAndStyle  implements Serializable {

	public ImagesAndStyle(int id) {
		super();
		this.id = id;
	}
	public ImagesAndStyle() {
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7069987513498382432L;

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="true" length="255"
	 */
	private String imageUrl;
	
	/**
	 * @hibernate.property length="50"
	 */
	private String name;
	/**
	 * @hibernate.many-to-one column="productId" class="com.hnfealean.sport.model.product.Product"
	 */

	private Product product;

	/**
	 * @hibernate.property
	 */
	private boolean visible = true;
	/**
	 * @hibernate.property
	 */	
	private boolean displayAsDefault;
	

	public boolean isDisplayAsDefault() {
		return displayAsDefault;
	}
	public void setDisplayAsDefault(boolean displayAsDefault) {
		this.displayAsDefault = displayAsDefault;
	}
	@SearchableId
	public int getId() {
		return id;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getImageUrl() {
		return imageUrl;
	}

	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getName() {
		return name;
	}
	@SearchableReference
	public Product getProduct() {
		return product;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
