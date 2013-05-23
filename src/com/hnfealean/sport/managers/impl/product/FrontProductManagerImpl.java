package com.hnfealean.sport.managers.impl.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import com.hnfealean.sport.managers.product.FrontProductManager;
import com.hnfealean.sport.model.deliver.DistributionTemplate;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.CategoryAttributeOption;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Manufacturer;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.ProductDetailGroup;
import com.hnfealean.sport.model.product.Size;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.pageset.SystemContext;
import com.hnfealean.sport.web.SeoUrl;
public class FrontProductManagerImpl extends CommonManager implements
		FrontProductManager {

	public String findCategoryName(String title){
		//String tt=(String)getSession().createQuery("select c.name from Category c where c.url='"+title.trim()+"'").uniqueResult();
		String tt=(String)getSession().createQuery("select c.name from Category c where c.url=?")
					.setParameter(0, title.trim()).uniqueResult();
		return tt;
	}
	public List searchChildCategories(String categoryName) {
		Query query;
		if(categoryName==null || categoryName.trim().length()==0){
			query = getSession().createQuery("from Category c where c.parent is null");
		}else{
			//query = getSession().createQuery("from Category c where c.parent.url='"+categoryName.trim()+"'");
			query = getSession().createQuery("from Category c where c.parent.url=?")
				.setParameter(0, categoryName.trim());
		}
		List list = query.list();
		return list;

	}

	public List searchAllChildProductIdBycUrl(String parentCategoryName){
		Query query =null;
		if(parentCategoryName!=null&&parentCategoryName.trim().length()>0){
			//query= getSession().createQuery("select c.id from Category c where c.url ='" + parentCategoryName.trim()+"'");
			query= getSession().createQuery("select c.id from Category c where c.url =?")
								.setParameter(0, parentCategoryName.trim());
			List list = query.list();	
			int[] categoryId;
			//System.out.print(list.toString());
			if(list.size()==0){
				
				return null;
			}
			StringBuffer categoryIds = new StringBuffer();
			int i=0;
			while(list.size()>0){
				for (Iterator iter=list.iterator(); iter.hasNext();i++) {
					categoryIds = new StringBuffer();
					int categoryid =(Integer)iter.next();
					categoryIds.append(categoryid).append(",");//1,
																//1,2,
				}
			query= getSession().createQuery("select c.id from Category c where c.parent.id in("+categoryIds.substring(0, categoryIds.length()-1).toString()+") and c.parent.children.size>0");
			//query= getSession().createQuery("select c.id from Category c where c.parent.id in(?) and c.parent.children.size>0")
			//	.setParameter(0, list);
			////	query= getSession().createQuery("select c.id from Category c where c.parent.id in(?) and c.parent.children.size>0")
			//						.setParameter(0, categoryIds.substring(0, categoryIds.length()-1).toString());
				list = query.list();	
			}
		//	System.out.println(list.toString());
			query = getSession().createQuery("select p.id from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.category.id in(" +categoryIds.substring(0, categoryIds.length()-1).toString()+")");
			List pIds = query.list();
			return pIds;
		}
		return null;
	}
	public PageModel searchChildProductsAll(String parentCategoryName,String orderBy) {
		//SystemContext.setOffset(offsetvalue);
		//SystemContext.setPagesize(6);
		Query query =null;
		if(parentCategoryName!=null&&parentCategoryName.trim().length()>0){
			List list = searchAllChildCategoriesId(parentCategoryName.trim());
			if(list==null||list.size()==0){
				return null;
			}
			String ids = list.toString();
			PageModel p0=searchPaginated("from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.category.id in("+ids.substring(1,ids.length()-1)+")"+ orderBy , null,SystemContext.getOffset(),SystemContext.getPagesize());
			return p0;
		}
			/*
			query= getSession().createQuery("select c.id from Category c where c.url ='" + parentCategoryName.trim()+"'");
			List list = query.list();	
			int[] categoryId;
			System.out.print(list.toString());
			if(list.size()==0){
				
				return new PageModel();
			}
			StringBuffer categoryIds = new StringBuffer();
			int i=0;
			while(list.size()>0){
				categoryIds = new StringBuffer();
				for (Iterator iter=list.iterator(); iter.hasNext();i++) {
					
					int categoryid =(Integer)iter.next();
					categoryIds.append(categoryid).append(",");//1,
																//1,2,
				}
				System.out.print(categoryIds.substring(0, categoryIds.length()));
			//	query= getSession().createQuery("select c.id from Category c");
			query= getSession().createQuery("select c.id from Category c where c.parent.id in("+categoryIds.substring(0, categoryIds.length()-1).toString()+") and c.parent.children.size>0");
			query.setFirstResult(SystemContext.getOffset());
			query.setMaxResults(SystemContext.getPagesize());
				list = query.list();	
				
			}
			System.out.println("from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.category.id in("+categoryIds.substring(0, categoryIds.length()-1).toString()+")"+ orderBy );
			PageModel p=searchPaginated("from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.category.id in("+categoryIds.substring(0, categoryIds.length()-1).toString()+")"+ orderBy , null,SystemContext.getOffset(),SystemContext.getPagesize());
			return p;
		}*/else{
			query= getSession().createQuery("select c.id from Category c");
			PageModel p=searchPaginated("from Product p where p.visible=true and p.imagesAndStyles.size>0"+ orderBy , null,SystemContext.getOffset(),SystemContext.getPagesize());	
			return p;
		}

	}

	public List searchBrandsAll(String orderBy) {
		return null;
//		Query query = getSession().createQuery("from Brand b where b.id in (select p.brand.id from Product p where p.visible=true and p.imagesAndStyles.size>0)");
//
//		List list = query.list();
//		return list;
	}

	/**
	 * 查询网站所有产品
	 */
	public PageModel searchProductsAll() {
		//SystemContext.setPagesize(6);
	//	System.out.println(SystemContext.getOffset());
	//	System.out.println(SystemContext.getPagesize());
		//	return searchPaginated("from Product p where p.productStyles.size > 0 and p.productStyles.visible=true", null,SystemContext.getOffset(),SystemContext.getPagesize());
			return searchProductsAll(null,SystemContext.getOffset(),SystemContext.getPagesize());
	}
	
	public PageModel searchProductsAll(String orderBy) {
	//	SystemContext.setPagesize(6);
	//	System.out.println(SystemContext.getOffset());
	//	System.out.println(SystemContext.getPagesize());
		//	return searchPaginated("from Product p where p.productStyles.size > 0 and p.productStyles.visible=true", null,SystemContext.getOffset(),SystemContext.getPagesize());
			return searchProductsAll(orderBy , SystemContext.getOffset(),SystemContext.getPagesize());
	}
	public PageModel searchProductsAll(String orderBy,Integer offSet,Integer pageSize) {
		
		PageModel pm=searchPaginated("from Product p where p.visible=true"+ orderBy , null,SystemContext.getOffset(),SystemContext.getPagesize()); 
		for(Product p:(List<Product>)pm.getDatas()){
			p.setShtml_File_Name(SeoUrl.createProductUrl(p.getShtml_File_Name(),p.getId()));
		}
		return pm;
		}
	
	public ImagesAndStyle findProductImagesAndStyleByProductId(Integer productId) {

		return (ImagesAndStyle) getSession().createQuery("from ImagesAndStyle image where image.visible=true and image.displayAsDefault=true and image.product.id=?")
									.setParameter(0, productId).setMaxResults(1).uniqueResult();
	}

	public List searchChildBrandsAll(String categoryName) {
		List tempIds=searchAllChildProductIdBycUrl(categoryName);
		Query query = null;
		if(tempIds==null)
			return null;
		String pIds = tempIds.toString();
		//System.out.println(pIds);
		if(pIds.length()>2){
			query = getSession().createQuery("from Brand b where b.id in (select p.brand.id from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.id in("+pIds.substring(1, pIds.length()-1)+"))");
		}else{
			return null;
		}
		List list = query.list();
		return list;
	}
	
	public List searchChildBrandsAll(List productIds) {
		//List tempIds=productIds;
		Query query = null;
		String pIds = productIds.toString();
		//System.out.println(pIds);
		if(pIds.length()>2){
			query = getSession().createQuery("from Brand b where b.id in (select p.brand.id from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.id in("+pIds.substring(1, pIds.length()-1)+"))");
		}else{
			return null;
		}
		List list = query.list();
		return list;
	}
	public List searchDirectChildCategoriesId(String categoryName) {
		/**
		 * 查找直接子分类的id
		 * 输入:父分类名称
		 * 返回:直接子分类id列表
		 */
		Query query;
		if(categoryName==null || categoryName.trim().length()==0){
			query = getSession().createQuery("select c.id from Category c where c.parent is null");
		}else{
			query = getSession().createQuery("select c.id from Category c where c.parent.url='"+categoryName.trim()+"'");
		}
		List list = query.list();
		return list;
	}
	
	public List searchAllChildCategoriesId(String parentCategoryUrl){
		/**
		 * 查找所有子分类的id
		 * 输入:父分类名称
		 * 返回:所有子分类id列表
		 */	
		Query query =null;
		if(parentCategoryUrl!=null&&parentCategoryUrl.trim().length()>0){
			//query= getSession().createQuery("select c.id from Category c where c.url ='" + parentCategoryName.trim()+"'");
			query= getSession().createQuery("select c.id from Category c where c.url =?")
								.setParameter(0, parentCategoryUrl.trim());
			List list = query.list();	
			int[] categoryId;
			//System.out.print(list.toString());
			if(list.size()==0){
				
				return null;
			}
			List cIds=new ArrayList();
			StringBuffer categoryIds = new StringBuffer();
			int i=0;
			while(list.size()>0){
				for (Iterator iter=list.iterator(); iter.hasNext();i++) {
					categoryIds = new StringBuffer();
					int categoryid =(Integer)iter.next();
					categoryIds.append(categoryid).append(",");//1,
																//1,2,
					cIds.add(categoryid);
				}
				//pIds.substring(1, pIds.length()-1)
		
				query= getSession().createQuery("select c.id from Category c where c.parent.id in("+categoryIds.substring(0, categoryIds.length()-1).toString()+") and c.parent.children.size>0");
				//query= getSession().createQuery("select c.id from Category c where c.parent.id in("+cIds.toString()+") and c.parent.children.size>0");
				list = query.list();	
			}
			//System.out.println(list.toString());
			//	query = getSession().createQuery("select p.id from Product p where p.visible=true and p.imagesAndStyles.size>0 and p.category.id in(" +categoryIds.substring(0, categoryIds.length()-1).toString()+")");
			//List pIds = query.list();
			return cIds;
		}
		return null;
		
	}
	public List searchAllProductsId(List categoryIds){
		/**
		 * 查找指定分类下所有产品ID
		 * 输入:指定的分类ID数组
		 * 返回:产品ID数组
		 */
		
		return null;
	}
/*	输入类别的url，得到position数组
 * public List getPositon(String categoryName){
		List<Category> position =new ArrayList<Category>();
		Category category =(Category) getSession().createQuery(select new Category(parent,id,imageUrl,name,url)
				"from Category c where c.url=?").setParameter(0,categoryName).uniqueResult();
		if(category ==null){
			return null;
		}
		while(category!=null){
			position.add(category);
			category=category.getParent();
		}
		for(Category temp:position){
			temp.setUrl(SeoUrl.createCategoryUrl(temp.getUrl()));
		}
		return position;
	}*/
public int getCategoryIdByTitle(String categoryName) {
		
		return (Integer)getSession().createQuery("select id from Category where url=?").setParameter(0, categoryName)
				.uniqueResult();
	}
	public int getCategoryIdBycName(String categoryName) {
		
		return (Integer)getSession().createQuery("select id from Category where name=?").setParameter(0, categoryName)
				.uniqueResult();
	}
	public Category getCategoryByUrl(String url) {
		Category category =(Category) getSession().createQuery("from Category where url=?").setParameter(0, url)
		.setMaxResults(1).uniqueResult();
	//	if(category!=null)category.setUrl(SeoUrl.createCategoryUrl(category.getUrl()));
		return category;
	//	return null;
	}
	/**
	 * 查询分类ID号
	 * @param url
	 * @return
	 */
	public Integer getCategoryIdByUrl(String url){
		if(url==null||url.trim().length()==0)	return 0;
		return (Integer)getSession().createQuery("select id from Category where url=?")
							.setParameter(0, url.trim())
							.uniqueResult();
	}
	/**
	 * 查询与当前分类同级的分类
	 * @param id
	 * @return
	 */
	public List<Category> searchSameLevelCategories(Integer id){
		int parentId=0;
	Category parentCategory = (Category)getSession().createQuery("select parent from Category category where category.id=?")
		.setParameter(0, id).uniqueResult();
	if(parentCategory==null){
		parentId=0;
	}else{
		parentId=parentCategory.getId();
	}
		if(parentId==0){
			List<Category> list = (List<Category>)getSession().createQuery(
			"select new Category(description,id,imageUrl,name,url) from Category where parent is null")
			.list();
			for(Category temp:list){
				temp.setUrl(SeoUrl.createCategoryUrl(temp.getUrl(),temp.getId()));
			}
			return list;
		}
		List<Category> list = (List<Category>)getSession().createQuery(
		"select new Category(description,id,imageUrl,name,url) from Category where parent.id=?")
		.setParameter(0, parentId)
		.list();
		for(Category temp:list){
			temp.setUrl(SeoUrl.createCategoryUrl(temp.getUrl(),temp.getId()));
		}
		return list;
	}
	/**
	 * 查询当前分类的直接子目录
	 * @param id
	 * @return
	 */
	public List<Category> searchDirectChildCategories(Integer id){
		List<Category> list =(List<Category>)getSession().createQuery(
				"select new Category(description,id,imageUrl,name,url) from Category where parent.id=?")
				.setParameter(0, id).list();
		for(Category temp:list){
			temp.setUrl(SeoUrl.createCategoryUrl(temp.getUrl(),temp.getId()));
		}
		return list;
	}
	/**
	 * 查询所有一级目录
	 * @return
	 */
	public List<Category> searchAllTopCategories(){
		List<Category> list = (List<Category>)getSession().createQuery("select new Category(description,id,imageUrl,name,url) from Category where parent=null")
				.list();
		for(Category temp:list){
			temp.setUrl(SeoUrl.createCategoryUrl(temp.getUrl(),temp.getId()));
		}
		return list;
	}
	public List<Integer> searchDirectChildCategoryIds(Integer id){
		return (List<Integer>)getSession().createQuery("select id from Category where parent.id=?")
				.setParameter(0, id)
				.list();	
	}
	public PageModel searchProductByCategoryIds(String ids,String filter,String orderBy,Integer offset,Integer pagesize){
		return searchProductByCategoryIds(ids, filter, orderBy);
	}
	/**
	 * 根据分类ids查找所有产品,ids示例 1,2,3,添加了filter和orderby参数
	 * @param ids
	 * @param filter
	 * @param orderBy
	 * @return
	 */
	public PageModel searchProductByCategoryIds(String ids,String filter,String orderBy){
		if(orderBy==null||orderBy.trim().length()==0){
			//return searchProductByCategoryIds(ids, filter);
			orderBy="";
		}
		PageModel pageModel = new PageModel();
		if(filter==null||filter.length()==0){
			int total =	((Long)getSession().createQuery("select count(id) from Product where visible=true and category.id in ("+ids+")")
				.uniqueResult()).intValue();
			pageModel.setTotal(total);
			pageModel.setDatas(
					getSession().createQuery(
					"select new Product(id, marketPrice,meta_Description,name,sellPrice,shtml_File_Name) from Product where visible=true and category.id in ("+ids+")"+orderBy)
					.setFirstResult(SystemContext.getOffset())
					.setMaxResults(SystemContext.getPagesize())
					.list()
					);
		}else{
		int total =	((Long)getSession().createQuery("select count(id) from Product where visible=true and category.id in ("+ids+") and filter like ?")
				.setParameter(0, filter.replace("_0", "_%"))
				.uniqueResult()).intValue();
		pageModel.setTotal(total);
		pageModel.setDatas(
				getSession().createQuery(
				"select new Product(id, marketPrice,meta_Description,name,sellPrice,shtml_File_Name) from Product where visible=true and category.id in ("+ids+") and filter like ?"+orderBy)
				.setParameter(0, filter.replace("_0", "_%"))
				.setFirstResult(SystemContext.getOffset())
				.setMaxResults(SystemContext.getPagesize())
				.list()
				);
		}
		for(Product temp:(List<Product>)pageModel.getDatas()){
			temp.setShtml_File_Name(SeoUrl.createProductUrl(temp.getShtml_File_Name(),temp.getId()));
		}
		return pageModel;
	}
	/**
	 * 根据分类ids查找所有产品,
	 * ids示例 1,2,3
	 */
	public PageModel searchProductByCategoryIds(String ids,String filter){
		return searchProductByCategoryIds(ids, filter, null);
	/*	PageModel pageModel = new PageModel();
		if(filter==null||filter.length()==0){
			int total =	((Long)getSession().createQuery("select count(id) from Product where category.id in ("+ids+")")
				.uniqueResult()).intValue();
			pageModel.setTotal(total);
			pageModel.setDatas(
					getSession().createQuery(
					"select new Product(id, marketPrice,name,sellPrice,shtml_File_Name) from Product where category.id in ("+ids+")")
					.setFirstResult(SystemContext.getOffset())
					.setMaxResults(SystemContext.getPagesize())
					.list()
					);
		}else{
		int total =	((Long)getSession().createQuery("select count(id) from Product where category.id in ("+ids+") and filter like ?")
				.setParameter(0, filter.replace("_0", "_%"))
				.uniqueResult()).intValue();
		pageModel.setTotal(total);
		pageModel.setDatas(
				getSession().createQuery(
				"select new Product(id, marketPrice,name,sellPrice,shtml_File_Name) from Product where category.id in ("+ids+") and filter like ?")
				.setParameter(0, filter.replace("_0", "_%"))
				.setFirstResult(SystemContext.getOffset())
				.setMaxResults(SystemContext.getPagesize())
				.list()
				);
		}
		
		getSession().createQuery(
		"select new Product(id,marketPrice,name,sellPrice,shtml_File_Name) from Product where id in (?)")
		.setParameter(0, ids).list();
		
		return pageModel;*/
	}
	/**
	 * 根据产品ID查询其图片，只返回第一张
	 */
	public ImagesAndStyle getImageByProductId(Integer id){
	ImagesAndStyle image =(ImagesAndStyle) getSession().createQuery("from ImagesAndStyle where product.id=?").setParameter(0, id)
		.setMaxResults(1).uniqueResult();
		//getSession().flush();
	//System.out.println("load image "+image.getId());
	id=image.getId();//此语句是为了使hibernate立即执行sql
		return image;
		
	}
	public List getPositon(Integer categoyId){
		List<Category> position =new ArrayList<Category>();
		Category category =(Category) getSession().createQuery(/*select new Category(parent,id,imageUrl,name,url)*/
				"from Category c where c.id=?").setParameter(0,categoyId).uniqueResult();
		if(category ==null){
			return null;
		}
		while(category!=null){
			position.add(category);
			category=category.getParent();
		}
		for(Category temp:position){
			temp.setUrl(SeoUrl.createCategoryUrl(temp.getUrl(),temp.getId()));
		}
		return position;
	}
	
	public List<CategoryAttributeOption> getCatrgoryAttributeOptions(Integer categoyId){
		return (List<CategoryAttributeOption>)getSession().createQuery("from CategoryAttributeOption option where option.attribute.id=? order by option.id")
					.setParameter(0, categoyId)
					.list();
		
	}
	public PageModel searchChildProductsAll(Integer categoryId) {
		PageModel pm = new PageModel();
		int count = ((Long)getSession().createQuery("select count(p.id) from Product p where p.category.id in("+
				searchAllChildCategoriesId(categoryId)
				+")")
				.uniqueResult()).intValue();
		pm.setTotal(count);
		if(count==0){
			return pm;
		}
		List<Product> products = getSession().createQuery("select new Product(id,meta_Description,meta_KeyWords,name,shtml_File_Name,titleInPage) from Product p where p.category.id in("+
				searchAllChildCategoriesId(categoryId)
				+") order by id desc")
				.list();
		pm.setDatas(products);
		return pm;
	}
	public String searchAllChildCategoriesId(Integer categoryId) {
		
		String returnStr = categoryId+","+searchAllChildCategoriesIdNoTop(categoryId);
		if(returnStr==null||returnStr.trim().length()==0)return "";
		if(returnStr.trim().length()>2){
			if(returnStr.endsWith(",")){
				return returnStr.substring(0, returnStr.length()-1);
			}
		}
		System.out.println(returnStr);
		return returnStr;
	}
	private String searchAllChildCategoriesIdNoTop(Integer categoryId) {
			StringBuffer ids = new StringBuffer();
			 List<Integer> childrenIds =getSession().createQuery("select c.id from Category c where c.parent.id =?")
								.setParameter(0, categoryId).list();
			 if(childrenIds==null||childrenIds.size()==0)	return categoryId+",";
			 for(int id:childrenIds){
				 String temp =searchAllChildCategoriesIdNoTop(id);
				 if(!temp.equals(id+",")){
					 ids.append(id+","+temp);
				 }else{
					 ids.append(temp);
				 }
			 }
//			 if(ids==null||ids.length()==0){
//				 return null;
//			 }else if(ids.charAt(ids.length()-1)==",".charAt(0)){
//				 ids.deleteCharAt(ids.length()-1);
//			 }
			return ids.toString();
			}
	public List<Product> getProductsByIdString(String ids) {
		if(ids==null||ids.trim().length()==0)	return null;
		List<Product> list =(List<Product>)getSession().createQuery("from Product where id in ("+ids+")")
		.list();
		for(Product temp:list){
			temp.setShtml_File_Name(SeoUrl.createProductUrl(temp.getShtml_File_Name(),temp.getId()));
		}
		return  list;
	}
	public Product getProductById(Integer id) {
		//return (Product)getHibernateTemplate().get(Product.class, id);
		Product p =(Product) getSession().createQuery("from Product p where p.id=?")
										.setParameter(0, id).uniqueResult();
		if(p!=null)	p.setShtml_File_Name(SeoUrl.createProductUrl(p.getShtml_File_Name(),p.getId()));
		return p;
	}
	public Product getProductByUrl(String url) {
		Product p = (Product)getSession().createQuery("from Product p where p.shtml_File_Name=?")
		.setParameter(0, url)
		.setMaxResults(1)
		.uniqueResult();
		if(p!=null)p.setShtml_File_Name(SeoUrl.createProductUrl(p.getShtml_File_Name(),p.getId()));
		 return p;
	}
	public Category getCategoryByProductId(Integer productId) {
		Category category =	(Category)getSession().createQuery("select p.category from Product p where p.id=?")
					.setParameter(0, productId)
					.uniqueResult();
		//if(category!=null)category.setUrl(SeoUrl.createCategoryUrl(category.getUrl()));
		return category;
	}
	public List<AttributeOption> getAttributeOptionsByProductId(
			Integer productId) {
		List<AttributeOption> options = (List<AttributeOption>)getSession().createQuery(
				"select p.options from Product p where p.id=?")
				.setParameter(0, productId)
				.list();
		return options;
	}
	public Brand getBrandByProductId(Integer productId) {
		return(Brand)getSession().createQuery("select p.brand from Product p where p.id=?")
			.setParameter(0, productId).uniqueResult();
	}
	public List<ProductDetailGroup> getDetailGroupsByProductId(Integer productId) {
		return(List<ProductDetailGroup>)getSession().createQuery("select p.groups from Product p where p.id=?")
		.setParameter(0, productId).list();
	}
	public DistributionTemplate getDistributionTemplateByProductId(
			Integer productId) {
		return(DistributionTemplate)getSession().createQuery("select p.distributionTemplate from Product p where p.id=?")
		.setParameter(0, productId).uniqueResult();
	}
	public Set<ImagesAndStyle> getImagesAndStylesByProductId(Integer productId) {
		Set<ImagesAndStyle> styles = new HashSet<ImagesAndStyle>();
		List<ImagesAndStyle> temps = getSession().createQuery("select p.imagesAndStyles from Product p where p.id=?")
		.setParameter(0, productId).list();
		for(ImagesAndStyle style:temps){
			styles.add(style);
		}
		return styles;
	}
	public Set<Manufacturer> getManufacturersByProductId(Integer productId) {
		Set<Manufacturer> manufacturers =  new HashSet<Manufacturer>();
		List<Manufacturer>  temps =getSession().createQuery("select p.manufacturers from Product p where p.id=?")
		.setParameter(0, productId).list();
		for(Manufacturer temp:temps){
			manufacturers.add(temp);
		}
		return manufacturers;
	}
	public Set<Size> getSizesByProductId(Integer productId) {
		Set<Size> sizes = new HashSet<Size>();
		List<Size> temps = getSession().createQuery("select p.sizes from Product p where p.id=?")
		.setParameter(0, productId)
		.list();
		for(Size temp: temps){
			sizes.add(temp);
		}
		return sizes;
	}
	public Category getCategoryById(Integer id) {
		return (Category)getSession().createQuery("from Category where id=?")
					.setParameter(0, id).uniqueResult();
	}
}
