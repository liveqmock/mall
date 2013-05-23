package com.hnfealean.sport.web.forms.user;

import org.apache.struts.action.ActionForm;

public class ModuleForm extends ActionForm {


	/**
	 * 
	 */
	private static final long serialVersionUID = 583923979929739469L;

	private int id;
	
	private int parentId;

	private String name;
	
	private int orderNo;
	
	private String sn;
	
	private String url;
	
	private String functionName;
	
	private int moduleFunctionId;
	
	private String moduleFunctionDescription;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getModuleFunctionId() {
		return moduleFunctionId;
	}

	public void setModuleFunctionId(int moduleFunctionId) {
		this.moduleFunctionId = moduleFunctionId;
	}

	public String getModuleFunctionDescription() {
		return moduleFunctionDescription;
	}

	public void setModuleFunctionDescription(String moduleFunctionDescription) {
		this.moduleFunctionDescription = moduleFunctionDescription;
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

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	
}
