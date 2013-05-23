package com.hnfealean.sport.model.deliver;

import java.util.Set;
/**
 * @hibernate.class table="t_globaldistributiontemplate"
 * @author Administrator
 *
 */
public class GlobalDistributionTemplate {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property length="255"
	 */
	private String name;

	/**
	 * @hibernate.property
	 */
	private boolean ignoreProductDeliverFee;
	
	/**
	 * @hibernate.property
	 */
	private boolean permanent;
	
	/**
	 * @hibernate.set order-by="id asc" lazy="extra" cascade="save-update"
	 * @hibernate.key column="globalDistributionId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.deliver.GlobalDeliverModule"
	 */
	private Set<GlobalDeliverModule> modules;
	/**
	 * @hibernate.property
	 */
	private boolean enable;

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
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

	public boolean isIgnoreProductDeliverFee() {
		return ignoreProductDeliverFee;
	}
	public boolean getIgnoreProductDeliverFee() {
		return ignoreProductDeliverFee;
	}
	public void setIgnoreProductDeliverFee(boolean ignoreProductDeliverFee) {
		this.ignoreProductDeliverFee = ignoreProductDeliverFee;
	}

	public boolean isPermanent() {
		return permanent;
	}

	public void setPermanent(boolean permanent) {
		this.permanent = permanent;
	}

	public Set<GlobalDeliverModule> getModules() {
		return modules;
	}

	public void setModules(Set<GlobalDeliverModule> modules) {
		this.modules = modules;
	}
}
