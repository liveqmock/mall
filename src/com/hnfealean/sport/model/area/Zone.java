package com.hnfealean.sport.model.area;

import java.util.Set;

/**
 * @hibernate.class table="t_zone"
 * @author Administrator
 *
 */
public class Zone {
	public Zone() {
		super();
	}
	public Zone(int countryId, String name){
		super();
		this.country = new Country(countryId);
		this.name = name;	
	}
	public Zone(int countryId, String name, int parentId) {
		super();
		this.country = new Country(countryId);
		this.name = name;
		this.parent = new Zone(parentId);
	}
	public Zone(int id) {
		super();
		this.id = id;
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
private int id;
/**
 *  @hibernate.many-to-one column="country"
 */
private Country country;
/**
 * @hibernate.property length="3"
 */
private String code;
/**
 * @hibernate.property length="30"
 */
private String name;
/**
 * @hibernate.many-to-one  column="parentId"
 */
private Zone parent;
public Zone getParent() {
	return parent;
}
public void setParent(Zone parent) {
	this.parent = parent;
}
public Set getChildren() {
	return children;
}
public void setChildren(Set children) {
	this.children = children;
}
/**
 * @hibernate.set order-by="id asc" inverse="true" lazy="true"
 * @hibernate.key column="parentId"
 * @hibernate.one-to-many class="com.hnfealean.sport.model.area.Zone"	
 */
private Set children;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public Country getCountry() {
	return country;
}
public void setCountry(Country country) {
	this.country = country;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
} 
}
