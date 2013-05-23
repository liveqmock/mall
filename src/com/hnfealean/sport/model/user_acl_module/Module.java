package com.hnfealean.sport.model.user_acl_module;

import java.util.Set;
/**
 * @hibernate.class table="t_module"
 * @author Administrator
 *
 */
public class Module {
	
	/**
	 * @hibernate.id
	 * 		generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property
	 * 		not-null="true" unique="true"
	 */
	private String name;
	
	/**
	 * 模块编号
	 * @hibernate.property
	 */
	private String sn;
	
	/**
	 * 模块入口地址
	 * @hibernate.property
	 */
	private String url;
	
	
	/**
	 * 模块显示顺序
	 * @hibernate.property
	 */
	private int orderNo;
	
	/**
	 * 模块的父模块
	 * @hibernate.many-to-one column="parentId"
	 */
	private Module parent;
	
	/**
	 * 模块的子模块
	 * @hibernate.set order-by="id asc" inverse="true" lazy="extra"	cascade="delete"
	 * @hibernate.key column="parentId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.user_acl_module.Module"
	 */
	private Set children;
	/**
	 * 模块的方法
	 * @hibernate.set order-by="id asc" inverse="true" lazy="extra" cascade="delete-orphan"
	 * @hibernate.key column="module"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.user_acl_module.ModuleFunction"
	 */
	private Set<ModuleFunction> functions;

	public Module() {
		super();
	}
	public Module(int id) {
		super();
		this.id = id;
	}

	public Set<ModuleFunction> getFunctions() {
		return functions;
	}

	public void setFunctions(Set<ModuleFunction> functions) {
		this.functions = functions;
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

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Module getParent() {
		return parent;
	}

	public void setParent(Module parent) {
		this.parent = parent;
	}

	public Set getChildren() {
		return children;
	}

	public void setChildren(Set children) {
		this.children = children;
	}
	
	

}
