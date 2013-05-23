package com.hnfealean.sport.managers.impl.product;


import java.util.List;

import org.hibernate.Query;

import com.hnfealean.sport.managers.product.CategoryManager;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;

public class CategoryManagerImpl extends CommonManager implements
		CategoryManager {

	/**
	 * 添加一条Category数据
	 */
	public void addCategory(Category category, int parentId) {
	
		if(searchPaginated("from Category c where c.name='"+category.getName().trim() +"'").getTotal()!= 0){
			
			throw new SystemException("添加失败，该名称已使用,请更换其他名称!");
		}
		if(parentId != 0){
			category.setParent((Category) getHibernateTemplate().load(Category.class, parentId));
		}
		getHibernateTemplate().save(category);


	}
	public int[] getProductIdsArray(int categoryId){
		List<Integer> list = (List<Integer> ) getSession().createQuery("select product.id from Product product where product.category.id=?")
			.setParameter(0, categoryId)
			.list();
		if(list==null||list.size()==0)	return null;
		int[] l = new int[(list.size())];
		int temp=0;
		for(int i:list){
			l[temp]=i;
			temp++;
		}
		return l;
	}
	public List<Integer> getProductIds(int categoryId){
		return (List<Integer> ) getSession().createQuery("select product.id from Product product where product.category.id=?")
			.setParameter(0, categoryId)
			.list();
	}
	
	
	public List<Product> getProducts(int categoryId) {
	
		List<Product> lists = getSession().createQuery("from Product where category.id=?")
		.setParameter(0, categoryId)
		.list();
		return lists;
	}
	public void delCategoryPermanently(int categoryId) {
		//getSession().createQuery("update ")
		getHibernateTemplate().delete(getHibernateTemplate().load(Category.class, categoryId));
	}
	
	/**
	 * 将制定Id的Category设为不可见
	 */
	public void delCategory(int categoryId) {
		
		Category category = (Category) getHibernateTemplate().load(Category.class, categoryId);
		
		category.setVisible(false);
		
		getHibernateTemplate().update(category);

	}

	/**
	 * 将指定parentId的所有直接子Category设为不可见
	 */
	public void delCategorys(int parentId) {
		Query query ;
		if(parentId == 0){
			query = getSession().createQuery("update Category c set c.visible=false where c.parent is null");	
		}else{
			query = getSession().createQuery("update Category c set pt.visible=false where c.parent.id="+parentId);
		}
		query.executeUpdate();

	}

	public Category findCategory(int categoryId) {
		if(categoryId == 0){
			return null;
		}
		Category category = (Category)getHibernateTemplate().get(Category.class, categoryId);
		return category;
//		return (Category)getSession().createQuery("from Category where id=?")
//		.setParameter(0, categoryId)
//		.setMaxResults(1).uniqueResult();
		/*.if(categoryId == 0){
			return null;
		}
		Category category = (Category)getHibernateTemplate().get(Category.class, categoryId);
		//System.out.println(category.getTitleInPage4category());
		return category;//(Category)getHibernateTemplate().get(Category.class, categoryId);
*/	}

	public PageModel searchCategorys(int parentId) {
	//	System.out.println("parentId:" + parentId);
		if(parentId == 0){
			return	searchPaginated("from Category c where c.parent is null");
	
		}else{
			return searchPaginated("from Category c where c.parent.id =?" , parentId );
		}
	}

	/**
	 * 按照名称查询多条Category
	 */
	public PageModel searchCategorys(String likeName) {
		return	searchPaginated("from Category p where p.name like '%" + likeName +"%'");
	}

	/**
	 * 更新Category
	 */
	public void updateCategory(Category category, int categoryId) {
		getHibernateTemplate().update(category);
/*		Category category = (Category) getHibernateTemplate().load(Category.class, categoryId);
//		if(p.getName().trim().equalsIgnoreCase(category.getName().trim()) ){
//			throw new SystemException("更新失败，该名称已使用,请更换其他名称!");
//		}
		if(searchPaginated("from Category p where p.name='"+p.getName().trim() +"' and p.id !=" + p.getId()).getTotal()!= 0){
			throw new SystemException("更新失败，该名称已使用,请更换其他名称!");
		}
		category.setName(p.getName().trim());
		category.setDescription(p.getDescription().trim());
		getHibernateTemplate().update(category);*/
	}

	public int addEasyPopulate(String name) {
		//System.out.println(name);
		Category categoryFound = (Category) getSession().createQuery("from Category c where c.name=?").setParameter(0, name.trim()).uniqueResult();
	//	System.out.println(categoryFound);
		if(categoryFound==null){
			Category category = new Category(name.trim());
			//System.out.println(category.getName());
			category.setParent(null);
			category.setTitleInPage4category(WebUtil.generateURL(name));
			//category.setTitleInPage4category(name.trim().replace('#','-').replace('&', '_').replace(' ', '_'));
			category.setUrl(WebUtil.generateURL(name));
			getHibernateTemplate().save(category);
		//	System.out.println(category.getId());
			return category.getId();
		}
//		return 1;
		return categoryFound.getId();
	}

	public boolean checkExist(String name) {
		// TODO Auto-generated method stub
		Category categoryFound = (Category) getSession().createQuery("from Category where name=?").setParameter(0, name).uniqueResult();
		//int id = (Integer)getSession().createQuery("select id from Category where name=?").setParameter(0, name).uniqueResult();
		if(categoryFound!=null)	return true;
		return false;
	}


	public Category getCategoryByName(String name) {

		return (Category) getSession().createQuery("from Category where name =?").setParameter(0, name.trim()).uniqueResult();
		//return null;
	}

	public Category addEasyCategory(String name) {
		Category c = (Category) getSession().createQuery("from Category where name=?").setParameter(0, name).uniqueResult();
		if(c==null){
			Category category = new Category(name);
			getHibernateTemplate().save(category);
			return category;
		}
		return c;
	}

}
