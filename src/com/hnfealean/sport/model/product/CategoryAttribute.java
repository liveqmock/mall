package com.hnfealean.sport.model.product;

import java.util.List;
import java.util.Set;
/**
 * @hibernate.class table="t_categoryAttribute"
 * @author Administrator
 *
 */
public class CategoryAttribute {
	public CategoryAttribute() {
		super();
		
	}
	public CategoryAttribute(int id) {
		super();
		this.id = id;
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="20"
	 */
	private String name;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="true" inverse="false"
	 * @hibernate.key column="attributeId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.CategoryAttributeOption"	
	 */
	private Set<CategoryAttributeOption> options;
	/**
	 * @hibernate.many-to-one column="categoryId"
	 */
	private Category category;
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

	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Set<CategoryAttributeOption> getOptions() {
		return options;
	}
	public void setOptions(Set<CategoryAttributeOption> options) {
		this.options = options;
	}


}
