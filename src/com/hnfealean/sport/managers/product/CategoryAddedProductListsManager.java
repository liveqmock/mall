package com.hnfealean.sport.managers.product;


import com.hnfealean.sport.model.product.CategoryAddedProductsListModule;


public interface CategoryAddedProductListsManager {
	
	public void add(int categoryId, int[] productsId,String type);
	public void update(int categoryId,int productId,String type,boolean addOrDel);
	public void delete(int categoryId,int[] productsId,String type);
	public String searchByCategoryId(Integer categoryId,String type);
	

	public void add(CategoryAddedProductsListModule module);

	public void update(CategoryAddedProductsListModule module);
	public void delete(CategoryAddedProductsListModule module);
}
