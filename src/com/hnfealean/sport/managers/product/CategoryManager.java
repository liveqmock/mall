package com.hnfealean.sport.managers.product;

import java.util.List;

import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;


	public interface CategoryManager {
		
		public List<Product> getProducts(int categoryId);
		public void delCategoryPermanently(int categoryId);
		public List<Integer> getProductIds(int categoryId);
		public int[] getProductIdsArray(int categoryId);
		public void addCategory(Category category,int parentId);
		
		public void delCategory(int categoryId);
		
		public void delCategorys(int parentId);
		
		public void updateCategory(Category p,int categoryId);
		
		public Category findCategory(int categoryId);
		
		public PageModel searchCategorys(int parentId);

		public PageModel searchCategorys(String likeName);
		public int addEasyPopulate(String name);
		public boolean checkExist(String name);
		public Category getCategoryByName(String name);
		public Category addEasyCategory(String name);
	}


