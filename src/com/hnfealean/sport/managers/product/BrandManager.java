package com.hnfealean.sport.managers.product;

import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.pageset.PageModel;

public interface BrandManager {

	public void addBrand(Brand brand);
	
//	public void addBrand(Brand brand, String imageUrl);
	
	public void delBrand(String id);
	
	public void delBrands(String[] id);
	
	public void updateBrand(Brand brand,String id);
	
	public Brand findBrand(String id);
	
	public PageModel searchBrands(String likeName);
	
	public PageModel searchBrandAll();
	public String addEasyPopulate(String name);
}
