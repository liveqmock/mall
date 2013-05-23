package com.hnfealean.sport.model.product;
/**
 * @hibernate.class
 * table="t_attributeoption"
 * @author Administrator
 *
 */
public class AttributeOption {

	/**
		@hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="10" not-null="true"
	 */
	private String value;
	
	/**
	 * @hibernate.many-to-one column="productId" class="com.hnfealean.sport.model.product.Product"
	 */
	
	private Product product;
	/**
	 * @hibernate.many-to-one column="attributeId" class="com.hnfealean.sport.model.product.ProductAttribute" lazy="false"
	 */

	private ProductAttribute attribute;
	
	public ProductAttribute getAttribute() {
		return attribute;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setAttribute(ProductAttribute attribute) {
		this.attribute = attribute;
	}
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AttributeOption other = (AttributeOption) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
