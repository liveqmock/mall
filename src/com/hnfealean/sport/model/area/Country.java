package com.hnfealean.sport.model.area;
/**
 * @hibernate.class
 * 	table="t_country"
 * @author Administrator
 *
 */
public class Country {
	public Country(){}
	public Country(int id) {
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
private String enName;
/**
 * @hibernate.property length="20"
 */
private String cnName;
/**
 * @hibernate.property length="5"
 */
private String countries_iso_code_2;
/**
 * @hibernate.property length="5"
 */
private String countries_iso_code_3  ;

public String getCountries_iso_code_2() {
	return countries_iso_code_2;
}
public void setCountries_iso_code_2(String countriesIsoCode_2) {
	countries_iso_code_2 = countriesIsoCode_2;
}
public String getCountries_iso_code_3() {
	return countries_iso_code_3;
}
public void setCountries_iso_code_3(String countriesIsoCode_3) {
	countries_iso_code_3 = countriesIsoCode_3;
}

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getEnName() {
	return enName;
}
public void setEnName(String enName) {
	this.enName = enName;
}
public String getCnName() {
	return cnName;
}
public void setCnName(String cnName) {
	this.cnName = cnName;
}
}
