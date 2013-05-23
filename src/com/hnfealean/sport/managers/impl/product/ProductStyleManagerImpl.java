package com.hnfealean.sport.managers.impl.product;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.hnfealean.sport.managers.product.ProductStyleManager;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;

public class ProductStyleManagerImpl extends CommonManager implements
		ProductStyleManager {

	public void updateVisible(int productStyleId) {

		ImagesAndStyle productStyle= (ImagesAndStyle)getHibernateTemplate().load(ImagesAndStyle.class, productStyleId);
	//	productStyle.isVisible() == true ? productStyle.setVisible(false) : productStyle.setVisible(true);
		if(productStyle.isVisible()){
			productStyle.setVisible(false);
		}else{
			productStyle.setVisible(true);
		}
		getHibernateTemplate().update(productStyle);
	}

	
	public void updateVisible(int[] productStyleIds, boolean visible) {

		Query query ;
		
		StringBuffer ids = new StringBuffer();
		for(int i=0;i< productStyleIds.length-1;i++){
			ids.append(productStyleIds[i]);		
			ids.append(",");	
		}
		ids.append(productStyleIds[productStyleIds.length-1]);	
		//ids.deleteCharAt(ids.length()-1);
		//ids.deleteCharAt(0);
		System.out.println(ids.toString());
		query = getSession().createQuery("update ImagesAndStyle p set p.visible=? where p.id in (?)")
		.setParameter(0, visible)
		.setParameter(1, ids.toString());

		query.executeUpdate();
	}
	
	public ImagesAndStyle findProductStyle(int productStyleId) {

		return (ImagesAndStyle)getHibernateTemplate().load(ImagesAndStyle.class, productStyleId);
		
	}

	public ImagesAndStyle findProductStyleByProductId(int productId) {

		return (ImagesAndStyle)getHibernateTemplate().load(ImagesAndStyle.class, productId);
		
	}
	
	public PageModel searchProductStyles(int productId) {
		if(productId == 0){
			throw new SystemException("非法操作！");
		}
		
		return searchPaginated("from ImagesAndStyle p where p.product.id =? order by p.visible desc",productId);
		
	}

	public void addProductStyle(ImagesAndStyle productStyle, int productId) {
		Product p = new Product();
		p.setId(productId);
		
		productStyle.setProduct(p);
		getHibernateTemplate().save(productStyle);
		
	}

	public void delProductStyle(ImagesAndStyle productStyle, int productId) {

		
	}

	public Set getProductStylesByProuctId(int id) {

		List l =getSession().createQuery("from ImagesAndStyle p where p.product.id =? order by p.visible desc")
		.setParameter(0, id).list();
		Set s = new HashSet();
		for(int i=0;i<l.size();i++){
			s.add(l.get(i));
		}
		return s;
}


	public void updateProductStyle(ImagesAndStyle productStyle, int productId) {
		
		ImagesAndStyle p = (ImagesAndStyle)getHibernateTemplate().load(ImagesAndStyle.class, productId);
		p = productStyle;
		getHibernateTemplate().update(p);
		
	}

	public String getImageUrl(int imagestyleid){
		String url = (String)getSession().createQuery("select imageUrl from ImagesAndStyle where id=?").setParameter(0, imagestyleid).uniqueResult();
		return url;
	}


	public List<String> getAllImages() {
		return (List<String>)getSession().createQuery("select imageUrl from ImagesAndStyle")
		.list();
		 
	}
}
