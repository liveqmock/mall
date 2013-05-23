package com.hnfealean.sport.web.forms.product;

import org.apache.struts.action.ActionForm;


public class StockForm extends ActionForm {

	private int id;
	
	private int[] ids;
	
	private int[] stocks;
	
	private int stock;

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

	public int[] getStocks() {
		return stocks;
	}

	public void setStocks(int[] stocks) {
		this.stocks = stocks;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
