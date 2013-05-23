package com.hnfealean.sport.managers.impl.shopping;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.shopping.ShoppingCartManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.Size;
import com.hnfealean.sport.web.SeoUrl;

public class ShoppingCartManagerImpl extends CommonManager implements
		ShoppingCartManager {
	public Product loadProduct(Integer productId){
		Product p  = new Product();
		//int id  =(Integer) getSession().createQuery("from Product where id = ?").setParameter(0, productId).uniqueResult();
		
		//p.setId(id);
		p=(Product)getSession().createQuery("select new Product(id,marketPrice,name,sellPrice,shtml_File_Name) from Product where id = ?").setParameter(0, productId).uniqueResult();
		//getHibernateTemplate().load(Product.class, productId);
		p.setShtml_File_Name(SeoUrl.createProductUrl(p.getShtml_File_Name(),p.getId()));
		return p;
	}
	public ImagesAndStyle loadImagesAndStyle(Integer imagesAndStyleId){
		return (ImagesAndStyle)getHibernateTemplate().get(ImagesAndStyle.class, imagesAndStyleId);
	}
	public Size loadSize(Integer sizeId){
		
		return (Size) getHibernateTemplate().get(Size.class, sizeId);
	}
	
	
	public AttributeOption getAttributeOption(Integer id) {
		return	(AttributeOption) getHibernateTemplate().get(AttributeOption.class, id);
	}

}
