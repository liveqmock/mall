package com.hnfealean.sport.web.forms.product.front;

import org.apache.struts.action.ActionForm;

public class ProductsSearchForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -839884909335100127L;
	private String key;
	private int pagesize=10;//显示数目
	private int offset=0;//从第几条开始查询
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
}
