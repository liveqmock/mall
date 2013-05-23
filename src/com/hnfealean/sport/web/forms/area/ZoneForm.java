package com.hnfealean.sport.web.forms.area;

import org.apache.struts.action.ActionForm;

public class ZoneForm extends ActionForm {
	private int id;
	private int[] ids;
	private String name;
	private String code;
	private int pId;
	private int countryId;
	private int returnto;
	public int getReturnto() {
		return returnto;
	}
	public void setReturnto(int returnto) {
		this.returnto = returnto;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}

}
