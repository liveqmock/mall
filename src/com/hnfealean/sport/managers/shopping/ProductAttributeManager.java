package com.hnfealean.sport.managers.shopping;

import java.util.List;

import com.hnfealean.sport.model.product.ProductAttribute;

public interface ProductAttributeManager {

	public ProductAttribute getById(int id);
	public boolean addAttribute(ProductAttribute attribute);
	
	public boolean deleteAttributeById(int id);
	
	public boolean updateAttribute(ProductAttribute attribute,int id); 
	public List<ProductAttribute> loadAllAttributes();
}
