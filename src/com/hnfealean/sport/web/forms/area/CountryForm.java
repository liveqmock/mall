package com.hnfealean.sport.web.forms.area;

import org.apache.struts.action.ActionForm;

public class CountryForm extends ActionForm {
private int id;
private int[] ids;
private String enName;
private String cnName;
private String countries_iso_code_2;
private String countries_iso_code_3;
public int getId() {
	return id;
}
public int[] getIds() {
	return ids;
}
public void setIds(int[] ids) {
	this.ids = ids;
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
}
