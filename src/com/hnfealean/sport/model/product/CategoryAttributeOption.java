package com.hnfealean.sport.model.product;

/**
 * @hibernate.class table="t_cattributeoption"
 * @author Administrator
 *
 */
public class CategoryAttributeOption {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="30"
	 */
	private String value;
	
	/**
	 * @hibernate.many-to-one column="attributeId" class="com.hnfealean.sport.model.product.CategoryAttribute"
	 */
	private CategoryAttribute attribute;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public CategoryAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(CategoryAttribute attribute) {
		this.attribute = attribute;
	}

}
