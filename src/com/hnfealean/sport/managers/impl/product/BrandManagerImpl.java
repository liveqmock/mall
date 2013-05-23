package com.hnfealean.sport.managers.impl.product;

import org.hibernate.Query;

import com.hnfealean.sport.managers.product.BrandManager;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;

public class BrandManagerImpl extends CommonManager implements BrandManager{



	public void addBrand(Brand brand) {

//		if(findBrand(brand.getId()) != null){
//			throw new SystemException("该名称已经被使用,请更换！");
//		}
		System.out.println(brand.getName());
		if(brand.getName() == null){
			throw new SystemException("品牌名称不能为空！");
		}
		System.out.println("开始添加");
		getHibernateTemplate().save(brand);
	}

	public void delBrand(String id) {


	}

	public void delBrands(String[] id) {


	}

	public Brand findBrand(String id) {
		return (Brand) getHibernateTemplate().load(Brand.class, id.trim());
	
	}
	
	public PageModel findBrand(String likeName,boolean findByName) {
		if(findByName){
			//return getHibernateTemplate().load(entityClass, id)
			//Query query = getSession().createQuery("from Brand b where b.name='"+ name +"'");
			return searchPaginated("from Brand b where b.name like +'%" +likeName +"%'");
		}
		return null;
	}

	public PageModel searchBrands(String likeName) {
		return searchPaginated("from Brand b where b.name like '%" + likeName +"%'");
	}

	public void updateBrand(Brand brand, String id) {
		//Brand b = new Brand();// =(Brand) getHibernateTemplate().load(Brand.class, id);
		
		if(searchPaginated("from Brand b where b.name='"+brand.getName().trim() +"' and b.id !='" + id +"'").getTotal()!= 0){
			throw new SystemException("更新失败，该名称已使用,请更换其他名称!");
		}
		Query query = null ;
		
		if(brand.getLogoUrl()!=null && brand.getLogoUrl().trim()!=""){
			query = getSession().createQuery("update Brand b set b.name=? , b.logoUrl=? where b.id=?")
								.setParameter(0, brand.getName().trim())
								.setParameter(1, brand.getLogoUrl().trim())
								.setParameter(2, id);		
		/*	query = getSession().createQuery("update Brand b set b.name='" + brand.getName().trim()+"' , b.logoUrl='"
						+ brand.getLogoUrl().trim() +"' where b.id='" + id +"'" 
			);	*/
		}else{
			query = getSession().createQuery("update Brand b set b.name=? where b.id=?")
			.setParameter(0,  brand.getName().trim())
			.setParameter(1, id)
			;
		/*	query = getSession().createQuery("update Brand b set b.name='" + brand.getName().trim()+"' where b.id='" + id +"'" 
		);				
			*/
		}
		query.executeUpdate();
		
//		System.out.println("brand.getName().trim()"+brand.getName().trim());
//		
//		b.setName(brand.getName().trim());
//		System.out.println("b.getName().trim()"+b.getName().trim());
//		if(brand.getLogoUrl() != null && !brand.getLogoUrl().equals("")){
//			b.setLogoUrl(b.getLogoUrl().trim());
//		}
//		System.out.println("b.getName().trim()"+b.getName().trim());
//		System.out.println("b.getName().trim()"+b.getId());
//		System.out.println("b.getName().trim()"+b.getLogoUrl());
//		getHibernateTemplate().update(b);
	}
//	public void addBrand(Brand brand, String imageUrl) {
//		Brand b =(Brand) getHibernateTemplate().load(Brand.class, brand.getId());
////		if(searchPaginated("from Brand b where b.name='"+brand.getName().trim() +"'").getTotal()!= 0){
////			throw new SystemException("添加失败，该名称已使用,请更换其他名称!");
////		}
//		
//		b.setName(brand.getName().trim());
//		
//		b.setLogoUrl(b.getLogoUrl().trim());
//		getHibernateTemplate().save(b);
//		
//		
//		File logosavedir = new File(logorealpathdir);
//		if(!logosavedir.exists()) logosavedir.mkdirs();//如果目录不存在就创建
//		
//		FileOutputStream fileoutstream = new FileOutputStream(logosavedir,imageUrl);
//		fileoutstream.write(formbean.getLogofile().getFileData());
//		fileoutstream.close();
//	}	
	public PageModel searchBrandAll() {

		return searchPaginated("from Brand");
	}

	public String addEasyPopulate(String name) {
		Brand brand = (Brand) getSession().createQuery("from Brand b where b.name=?").setParameter(0, name.trim()).uniqueResult();
		if(brand==null){
			Brand b = new Brand();
			b.setName(name);
			getHibernateTemplate().save(b);
			return b.getId();
		}
		return brand.getId();
	}
}
