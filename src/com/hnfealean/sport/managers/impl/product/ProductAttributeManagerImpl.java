package com.hnfealean.sport.managers.impl.product;

import java.util.List;

import com.hnfealean.sport.managers.shopping.ProductAttributeManager;
import com.hnfealean.sport.model.product.ProductAttribute;

public class ProductAttributeManagerImpl extends CommonManager implements ProductAttributeManager {

	public boolean addAttribute(ProductAttribute attribute) {
		getHibernateTemplate().save(attribute);
		return false;
	}

	public boolean deleteAttributeById(int id) {
		ProductAttribute productAttribute = (ProductAttribute)getHibernateTemplate().load(ProductAttribute.class, id);
		getSession().createQuery("update AttributeOption set attribute =null where product.id=?")
						.setParameter(0, productAttribute.getId()).executeUpdate();
		getSession().createQuery("delete AttributeOption where product is null").executeUpdate();
		getHibernateTemplate().delete(productAttribute);
		return true;
	}

	public List<ProductAttribute> loadAllAttributes() {
		
		return getSession().createQuery("from ProductAttribute").list();
	}

	public boolean updateAttribute(ProductAttribute attribute, int id) {
		getHibernateTemplate().update(attribute);
		return false;
	}

	public ProductAttribute getById(int id) {
		return (ProductAttribute) getHibernateTemplate().load(ProductAttribute.class, id);
		
	}

}
