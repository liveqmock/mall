package com.hnfealean.sport.model.deliver;

import java.util.Set;

import com.hnfealean.sport.model.product.Product;
/**
 * @hibernate.class table="t_distributiontemplate"
 * @author Administrator
 *
 */
public class DistributionTemplate {

	public DistributionTemplate() {
		super();
	}
	public DistributionTemplate(int id) {
		super();
		this.id = id;
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="extra" cascade="save-update"
	 * @hibernate.key column="distributionId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.deliver.DeliverModule"	
	 */	
	private Set<DeliverModule> delievrModules;
	
	/**
	 * @hibernate.property length="30" not-null="true"
	 */
	private String name;
	/**
	 * @hibernate.property length="255"
	 */
	private String description;
	/**
	 * @hibernate.set order-by="id asc" lazy="extra"
	 * @hibernate.key column="distributionId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.Product"
	 */
	private Set<Product> products;
	
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Set<DeliverModule> getDelievrModules() {
		return delievrModules;
	}
	public void setDelievrModules(Set<DeliverModule> delievrModules) {
		this.delievrModules = delievrModules;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
