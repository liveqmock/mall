package com.hnfealean.sport.model.product;

import java.io.Serializable;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

/**
 * @hibernate.class table="t_brand"
 * @author Administrator
 * 
 */
@Searchable(root = false)
public class Brand  implements Serializable {

	public Brand() {
		super();
	}

	public Brand(String id) {
		super();
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4137321508562817261L;

	/**
	 * @hibernate.id generator-class="uuid" length="32"
	 */
	private String id;

	/**
	 * @hibernate.property length="100"
	 */
	private String logoUrl;

	/**
	 * @hibernate.property not-null="true" unique="true" length="30"
	 */
	private String name;
	
	@SearchableId
	public String getId() {
		return id;
	}

	public String getLogoUrl() {
		return logoUrl;
	}
	
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public void setName(String name) {
		this.name = name;
	}

}
