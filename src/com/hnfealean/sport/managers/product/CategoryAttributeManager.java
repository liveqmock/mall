package com.hnfealean.sport.managers.product;

import java.util.List;

import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;

public interface CategoryAttributeManager {

	public List<CategoryAttribute> getCategoryAttributesByCId(Integer cId);
	public boolean addCategoryAttribute(CategoryAttribute att);
	public boolean addCategoryAttribute(CategoryAttribute att,Integer cId);
	public CategoryAttribute getCategoryAttributeByCId(Integer cId);
	public boolean updateCategoryAttribute(CategoryAttribute att);
	public boolean updateCategoryAttribute(CategoryAttribute att,Integer cId);
	public boolean deleteCategoryAttribute(Integer id);
	public boolean deleteCategoryAttribute(Integer attributeId,Integer categoryId);
	public List<CategoryAttribute> getAllCategoryAttributes(Integer id);
	public boolean addCategoryAttributeOption(CategoryAttributeOption option);
	public boolean addCategoryAttributeOption(CategoryAttributeOption option,Integer CategoryAttributeId);
	public List<CategoryAttributeOption> getCategoryAttributeOptionByAttributeId(Integer CategoryAttributeId);
	public boolean updateCategoryAttributeOption(CategoryAttributeOption option);
	public boolean updateCategoryAttributeOption(CategoryAttributeOption option,Integer CategoryAttributeId);
	public boolean deleteCategoryAttributeOption(Integer id);
	public CategoryAttributeOption getCategoryAttributeOptionById(Integer id);
	public boolean updateproductcategoryattribute(Integer productId,String filter);
	public void updateAllProductCategoryAttributeByCategoryId(Integer categoryId,String filter);
}
