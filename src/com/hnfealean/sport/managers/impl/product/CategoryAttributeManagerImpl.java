package com.hnfealean.sport.managers.impl.product;

import java.util.List;

import com.hnfealean.sport.managers.product.CategoryAttributeManager;
import com.hnfealean.sport.model.product.CategoryAttribute;
import com.hnfealean.sport.model.product.CategoryAttributeOption;

public class CategoryAttributeManagerImpl extends CommonManager implements
		CategoryAttributeManager {

	public boolean addCategoryAttribute(CategoryAttribute att) {

		getHibernateTemplate().save(att);
		return true;
	}

	public boolean addCategoryAttribute(CategoryAttribute att, Integer id) {

		
		return false;
	}

	public boolean addCategoryAttributeOption(CategoryAttributeOption option) {

		getHibernateTemplate().save(option);
		return false;
	}

	public boolean addCategoryAttributeOption(CategoryAttributeOption option, Integer attributeId) {
		
		return false;
	}

	public boolean deleteCategoryAttribute(Integer id) {
		getSession().createQuery("delete from CategoryAttribute where id=?").setParameter(0, id).executeUpdate();
		return false;
	}

	public boolean deleteCategoryAttributeOption(Integer id) {
		getSession().createQuery("delete from CategoryAttributeOption where id=?").setParameter(0, id).executeUpdate();
		
		return false;
	}

	public CategoryAttribute getCategoryAttributeByCId(Integer id) {
		
		return (CategoryAttribute) getHibernateTemplate().load(CategoryAttribute.class, id);
	}

	public List<CategoryAttributeOption>  getCategoryAttributeOptionByAttributeId(Integer attributeId) {

		return	(List<CategoryAttributeOption>) getSession().createQuery("from CategoryAttributeOption option where option.attribute.id=? order by option.id").setParameter(0, attributeId).list();
	//	return List<CategoryAttributeOption> getHibernateTemplate().load("CategoryAttributeOption.class", attributeId);
	//	return null;
	}



	public boolean updateCategoryAttribute(CategoryAttribute att) {

		getHibernateTemplate().update(att);
		return false;
	}

	public boolean updateCategoryAttribute(CategoryAttribute att, Integer id) {

		return false;
	}

	public boolean updateCategoryAttributeOption(CategoryAttributeOption option) {
		getHibernateTemplate().update(option);
		return false;
	}

	public boolean updateCategoryAttributeOption(CategoryAttributeOption option,
			Integer attributeId) {
		getHibernateTemplate().update(option);
		return false;
	}

	public List<CategoryAttribute> getCategoryAttributesByCId(Integer id) {
		
		return	(List<CategoryAttribute>) getSession().createQuery("from CategoryAttribute attribute where attribute.category.id=? order by attribute.id").setParameter(0, id).list();
		
	}

	public List<CategoryAttribute> getAllCategoryAttributes(Integer id) {
		if(id==0)
			return (List<CategoryAttribute>) getSession().createQuery("from CategoryAttribute attribute order by attribute.id").list();
		return (List<CategoryAttribute>) getSession().createQuery("from CategoryAttribute attribute where attribute.category.id=? order by attribute.id").setParameter(0, id).list();
	//	return (List<CategoryAttribute>) getSession().createQuery("from CategoryAttribute where cId=?").setParameter(0, id).list();
	
	}

	public CategoryAttributeOption getCategoryAttributeOptionById(Integer id) {
		return (CategoryAttributeOption)getHibernateTemplate().load(CategoryAttributeOption.class, id);
		
	}

	public boolean deleteCategoryAttribute(Integer attributeId, Integer categoryId) {
		CategoryAttribute categoryAttribute = (CategoryAttribute)getHibernateTemplate().load(CategoryAttribute.class, attributeId);
		categoryAttribute.getOptions().clear();
		categoryAttribute.setCategory(null);
		getHibernateTemplate().update(categoryAttribute);
		getSession().createQuery("delete CategoryAttributeOption where attribute=null").executeUpdate();
		getSession().createQuery("delete CategoryAttribute where category=null").executeUpdate();
		
//		if(attributeId==0||categoryId==0)	throw new SystemException("非法操作！");
//		getSession().createQuery("delete CategoryAttributeOption where attribute.id=?").setParameter(0, attributeId).executeUpdate();
//		getSession().createQuery("delete CategoryAttribute where id=? and category.id=?")
//		.setParameter(0, attributeId)
//		.setParameter(1, categoryId)
//		.executeUpdate();
		return true;
	}

	public boolean updateproductcategoryattribute(Integer productId, String filter) {
		getSession().createQuery("update Product set filter=? where id=?")
		.setParameter(0, filter).setParameter(1, productId).executeUpdate();
		return true;
	}

	public void updateAllProductCategoryAttributeByCategoryId(Integer categoryId,String filter) {
		getSession().createQuery("update Product set filter=? where category.id=? and filter is null")
		.setParameter(0, filter).setParameter(1, categoryId).executeUpdate();
	}

}
