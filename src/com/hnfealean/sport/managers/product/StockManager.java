package com.hnfealean.sport.managers.product;

import java.util.List;

import com.hnfealean.sport.pageset.PageModel;

public interface StockManager {

	public boolean setStockById(int stock,int pId);
	
	public int getStockById(int pId);
	
	public List getAllStock();
	public PageModel getStocks();
	public PageModel getStocks(int page,int pagesize);
	public boolean updateStock(int id,int value);
}
