package com.hnfealean.sport.managers.impl.product;


import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hnfealean.sport.managers.product.CategoryAddedProductListsManager;
import com.hnfealean.sport.model.product.CategoryAddedProductsListModule;

public class CategoryAddedProductListsManagerImpl extends CommonManager implements CategoryAddedProductListsManager {

	public void add(int categoryId, int[] productsId, String type) {
	}

	public void delete(int categoryId, int[] productsId, String type) {
		
		
	}

	public String searchByCategoryId(Integer categoryId, String type) {//productIds
		
		return (String) getSession().createQuery("select m.productIds from CategoryAddedProductsListModule m where m.categoryId =? and m.name=?)")
					.setParameter(0, categoryId)
					.setParameter(1, type).uniqueResult();
	
	//int l = ((CategoryAddedProductsListModule)categoryAddedProductsListModules.get(0)).getProductIds().length;
	//int[] productsids= ((CategoryAddedProductsListModule)categoryAddedProductsListModules.get(0)).getProductIds();
	/*
	 * for(int i=0;i<categoryAddedProductsListModules.size();i++){
		productsids=
	}
	*/
	/*
	if(productsids.length>0)
		return (List)getSession().createQuery("from Products where id in (?)")
				.setParameter(0, productsids.toString().substring(1, productsids.toString().length()-1)).list();
	else
		return null;
	*/
	//return null;
	}

	public void update(int categoryId, int productsId, String type,boolean addOrDel) {
		if(addOrDel==true){//addOrDel为true时，表示添加，为false时表示删除
		List<CategoryAddedProductsListModule> ms = (List<CategoryAddedProductsListModule>)getSession().createQuery("from CategoryAddedProductsListModule where name=? and categoryId=?")
		.setParameter(0, type)
		.setParameter(1, categoryId)
		.list();
		if(ms==null||ms.size()==0){
			CategoryAddedProductsListModule m = new CategoryAddedProductsListModule();
			m.setCategoryId(categoryId);
			m.setProductIds(productsId+"");
			m.setName(type);
			getHibernateTemplate().save(m);
			return;
		}
		if(ms.size()>1){//当返回的对象不止一条时，实际上我们必须确定针对每个categoryId和一个type，只有一条记录，因此，要将多出的记录删除
			Iterator<CategoryAddedProductsListModule> i =  ms.iterator();
			i.next();
			while(i.hasNext()){
				CategoryAddedProductsListModule temp =	(CategoryAddedProductsListModule)i.next();
				getHibernateTemplate().delete(temp);
			}
		}
		CategoryAddedProductsListModule module = (CategoryAddedProductsListModule)ms.iterator().next();
		
		String ids = module.getProductIds();
		if(ids==null||ids.length()==0){
			getSession().createQuery("update CategoryAddedProductsListModule set productIds =? where name=? and categoryId=?")
			.setParameter(0, productsId+"")
			.setParameter(1, type)
			.setParameter(2, categoryId)
			.executeUpdate();
		}
		Pattern p = Pattern.compile("(?:\\d,)*\\d");
		Matcher m = p.matcher(ids);
		
		if(!m.find())return;
		if(ids.indexOf(","+productsId+",")>0//当前产品id在ids中间，如 ,2,在  1,2,3中间
				||
			ids.startsWith(productsId+",")//当前产品id在ids起始部位，如 1,在 1,2,3起始
			||
			ids.endsWith(","+productsId)//当前产品id在ids末尾，如 3,在 1,2,3末尾
			||
			ids.equals(productsId+"")//当前产品id和ids相等，如 1 和1
				){
			//表示已含有，直接跳过
		}else{//不含有当前产品时，在ids末尾附上即可
			getSession().createQuery("update CategoryAddedProductsListModule set productIds =? where name=? and categoryId=?")
			.setParameter(0, ids+","+productsId)
			.setParameter(1, type)
			.setParameter(2, categoryId)
			.executeUpdate();
		}
		}else{
			List<CategoryAddedProductsListModule> ms = (List<CategoryAddedProductsListModule>)getSession().createQuery("from CategoryAddedProductsListModule where name=? and categoryId=?")
			.setParameter(0, type)
			.setParameter(1, categoryId)
			.list();
			if(ms==null||ms.size()==0)return;//没有记录，不需删除，直接返回
			if(ms.size()>1){//当返回的对象不止一条时，实际上我们必须确定针对每个categoryId和一个type，只有一条记录，因此，要将多出的记录删除
				Iterator<CategoryAddedProductsListModule> i =  ms.iterator();
				i.next();
				while(i.hasNext()){
					CategoryAddedProductsListModule temp =	(CategoryAddedProductsListModule)i.next();
					getHibernateTemplate().delete(temp);
				}
			}
			CategoryAddedProductsListModule module = (CategoryAddedProductsListModule)ms.iterator().next();
			String ids = module.getProductIds();
			if(ids==null||ids.length()==0){
				return;
			}
			Pattern p = Pattern.compile("(?:\\d,)*\\d");
			Matcher m = p.matcher(ids);
			
			if(!m.find())return;
			if(ids.endsWith(","+productsId))//当前产品id在ids末尾，如 3,在 1,2,3末尾
				ids = ids.substring(0, ids.lastIndexOf(","+productsId));
			else if(ids.indexOf(","+productsId+",")>0)//当前产品id在ids中间，如 ,2,在  1,2,3中间
				ids = ids.replace(","+productsId+",", ",");
			else if(ids.startsWith(productsId+","))//当前产品id在ids起始部位，如 1,在 1,2,3起始
				ids = ids.substring((productsId+",").length()-1);
			else if(ids.equals(productsId+""))
				ids="";
			getSession().createQuery("update CategoryAddedProductsListModule set productIds =? where name=? and categoryId=?")
					.setParameter(0, ids)
					.setParameter(1, type)
					.setParameter(2, categoryId)
					.executeUpdate();
		}
	}

	public void add(CategoryAddedProductsListModule module) {

		getHibernateTemplate().save(module);
		
	}

	public void delete(CategoryAddedProductsListModule module) {
		// TODO Auto-generated method stub
		
	}

	public void update(CategoryAddedProductsListModule module) {
		// TODO Auto-generated method stub
		
	}

	

}
