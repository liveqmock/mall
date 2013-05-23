package com.hnfealean.sport.managers.impl.product;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.product.ProductManager;
import com.hnfealean.sport.model.product.AttributeOption;
import com.hnfealean.sport.model.product.Brand;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.model.product.ProductAttribute;
import com.hnfealean.sport.model.product.ProductDetailAttribute;
import com.hnfealean.sport.model.product.ProductDetailGroup;
import com.hnfealean.sport.model.product.ProductDetailOption;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.ChineseToPinyinUtil;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.WebUtil;

public class ProductManagerImpl extends CommonManager implements ProductManager {


	public PageModel searchProducts(String likeName, int categoryId,
			String brandId, String code, float startBasePrice,
			float endBasePrice, float startSellPrice, float endSellPrice) {
		Query query;
		StringBuffer s = new StringBuffer();
		if(likeName!=null && !"".equals(likeName)){
			 s.append(" p.name like '%"+likeName + "%' and");
		}
		//按产品类型查询
		if(categoryId != 0 ){
			s.append(" p.type.id=" + categoryId + " and");
		}
		//按采购价区间查询
		if(startBasePrice >0){
			s.append(" p.basePrice>= " +startBasePrice + " and");
		}
		if(endBasePrice>0){
			s.append(" p.basePrice<= " + endBasePrice + " and");
		}
		//按销售价区间查询
		if(startSellPrice > 0){
			s.append(" p.sellPrice>=" + startSellPrice + " and");
		}
		if(endSellPrice>0){
			s.append(" p.sellPrice<=" + endSellPrice + " and");
		}
		//按货号查询
		if(code !=null && !"".equals(code)){
			s.append(" p.code='" + code +"'" + " and");
		}
		//按品牌查询
		if(brandId!=null && !"".equals(brandId)){
			s.append(" p.brand.id='" +brandId +"'" + " and");
		}
		
		System.out.println(s.toString());
		System.out.println(s.substring(s.length()-3, s.length()));
		if(s.substring(s.length()-3, s.length()).equals("and")){
			//s.substring(0, s.length()-3);
		}
	//	query=getSession().createQuery("from Product " + s.toString() + " order by visible desc");
		//List a = query.list();

		return searchPaginated("from Product p where" + s.substring(0, s.length()-3));
		//return null;
	}

	public void updateCommend(int id, boolean commend) {
		
		if(id == 0){
			throw new SystemException("非法操作！");
		}
		Product product = (Product) getHibernateTemplate().load(Product.class, id);
		product.setCommend(commend);
		getHibernateTemplate().update(product);
		
	}

	public void updateCommends(int[] ids, boolean commend) {
		
		if(ids == null){
			throw new SystemException("非法操作！");
		}
		StringBuffer s = new StringBuffer();
		for(int i=0; i<ids.length-1;i++){
			s.append(ids[i]);
			s.append(",");
		}
		s.append(ids[ids.length-1]);
		
		Query query;
		query = getSession().createQuery("update Product p set p.commend=" + commend + " where p.id in (" + s.toString() +")");

		query.executeUpdate();
	}

	public void updateVisible(int id, boolean visible) {
		
		if(id == 0){
			throw new SystemException("非法操作！");
		}
		Product product = (Product) getHibernateTemplate().load(Product.class, id);
		product.setCommend(visible);
		getHibernateTemplate().update(product);
	}

	public void updateVisibles(int[] ids, boolean visible) {
		if(ids == null){
			throw new SystemException("非法操作！");
		}
		StringBuffer s = new StringBuffer();
		for(int i=0; i<ids.length-1;i++){
			s.append(ids[i]);
			s.append(",");
		}
		s.append(ids[ids.length-1]);
		//System.out.println( s.toString());
		Query query;
		query = getSession().createQuery("update Product p set p.visible=" + visible + " where p.id in (" + s.toString() +")");

		query.executeUpdate();
	}
	public List<AttributeOption> getAttributeOptions(int productId,int attributeId) {
		return (List<AttributeOption>) getSession().createQuery("from AttributeOption aop where aop.product.id=? and aop.attribute.id=?")
		.setParameter(0, productId).setParameter(1, attributeId).list();
	}
	public void addProduct(Product product, String brandId, int categoryId) {
		if(brandId!=null&&brandId.length()>0)
		product.setBrand((Brand) getHibernateTemplate().load(Brand.class, brandId));
		product.setCategory((Category) getHibernateTemplate().load(Category.class, categoryId));
		for(ImagesAndStyle style:product.getImagesAndStyles()) {
			getHibernateTemplate().save(style);
		}
		//getHibernateTemplate().save(product.getImagesAndStyles());
		//product.sets
		getHibernateTemplate().save(product);
		
		
	}
	public void addImagesAndStyle(ImagesAndStyle image){
		getHibernateTemplate().save(image);
	}
	public void addAttributeOption(AttributeOption option) {
		getHibernateTemplate().save(option);
	}
	public void addProduct(Product product) {
		/*
		product.setBasePrice(100f);
		product.setBrand((Brand)getHibernateTemplate().load(Brand.class, "402808812373757901237375fb950001"));
		product.setAdditionInfo("买一送一");
		product.setCode("asdasd");
		product.setCommend(true);
		product.setCreateDate(new Date());
		product.setMeta_Description("no discription");
		product.setMarketPrice(500f);
		product.setModel("no model");
		product.setName("no name");
		//product.setProductStyles(productStyles)
		product.setSellCount(20);
		product.setSellPrice(400f);
		product.setSex("男女不限");
		product.setCategory((Category)getHibernateTemplate().load(Category.class, 1));
		product.setVisible(true);*/
		getHibernateTemplate().save(product);
		
		
	}

	public void delProduct(int id) {
		Product p = (Product)getHibernateTemplate().get(Product.class, id);
		if(p==null)	return;
		
		
		deleteProductFile(p.getShtml_File_Name());
		getHibernateTemplate().delete(p);
		clearDeleteProduct();	
	}
	public void deleteProductFile(String shtml_File_Name){
			String path = ProductManagerImpl.class.getResource("").toString();
			path=(path+"../../../../../../../../"+
			ConstantString.PRODUCT+ConstantString.SEPERATORSLASH+
			shtml_File_Name+
			ConstantString.PRODUCTFILESUFFIX).substring(6);
			//System.out.println(path);
			File file =new File(path);
			if(file.exists())file.delete();
}
	private void clearDeleteProduct() {
		getSession().createQuery("delete ImagesAndStyle where product is null").executeUpdate();
		getSession().createQuery("delete Manufacturer where product is null").executeUpdate();
		getSession().createQuery("delete Size where product is null").executeUpdate();		
		getSession().createQuery("delete AttributeOption where product is null").executeUpdate();
	}

	public void delProducts(int[] id) {
		if(id == null||id.length==0){
			return;
			//throw new SystemException("非法操作！");
		}
		StringBuffer s = new StringBuffer();
		for(int i=0; i<id.length-1;i++){
			s.append(id[i]);
			s.append(",");
		}
		s.append(id[id.length-1]);
		List<Product> l = (List)getSession().createQuery("from Product where id in (?)").setParameter(0, s.toString()).list();
		for(Product p:l){
			deleteProductFile(p.getShtml_File_Name());
			getHibernateTemplate().delete(p);
		}
		clearDeleteProduct();	
	}

	public Product findProduct(int id) {
		//	return (Product)getSession().createQuery("from Product where id=?").setParameter(0, id).uniqueResult();
		return (Product) getHibernateTemplate().get(Product.class, id);
	}

	public PageModel searchProductsAll() {
		return searchPaginated("from Product order by visible desc");
		
	}
	public List searchAllProducts() {
		return getSession().createQuery("from Product order by visible desc").list();
		
	}
	public PageModel searchProducts(String likeName) {
		return null;
	}
	public void updateDistribution(int id, int distributionId) {
		getSession().createQuery("update Product p set p.distributionTemplate.id=? where p.id=?")
		.setParameter(0, distributionId)
		.setParameter(1, id)
		.executeUpdate();
		return;
	}
	public void updateProduct(Product product, int categoryId) {
		getHibernateTemplate().update(product);
		return;
		
		//Product p = (Product) getHibernateTemplate().load(Product.class , id);//取出数据，原product
		//product.setImagesAndStyles(p.getImagesAndStyles());
	//	p = product;	
		
		//System.out.println(product.getId());
		//product.setCategory((Category)getHibernateTemplate().load(Category.class, categoryId));
//		if(product.getId()>0){
//		getHibernateTemplate().update(product);
//		}

	}
	public List<Category> getPositon(String url){
		List<Category> position =new ArrayList<Category>();
		Category category =(Category) getSession().createQuery("from Category c where c.url=?").setParameter(0,url).uniqueResult();
		if(category ==null){
			return null;
		}
		while(category!=null){
			position.add(category);
			category=category.getParent();
		}
		return position;
	}
	public PageModel searchProductsNameAndLinksByIds(List ids) {
		StringBuffer idsb = new StringBuffer();
		for(int i=0;i<ids.size();i++){
			idsb.append(ids.get(i));
		}
		searchProductsNameAndLinksByIds(idsb.toString());
		return null;
	}
	public PageModel searchProductsNameAndLinksByIds(String ids) {

		
		return	searchPaginated("select shtml_File_Name,name from Product where id in (?)",ids);
		//getSession().createQuery("select shtml_File_Name,name from Product where id in (?)")
		//.setParameter(0, ids)
		//.list();
	//	return null;
	}

	public PageModel searchProductsNameAndLinksByIds(int[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductAttribute> getAttributes() {
		return	(List<ProductAttribute>) getSession().createQuery("from ProductAttribute").list();
	}

	public List<AttributeOption> getAttributeOptionsByproductId(int productId) {
		return getSession().createQuery("from AttributeOption where product.id=?").setParameter(0, productId).list();

	}

	public AttributeOption getAttributeOptionById(int id) {
		return (AttributeOption)getHibernateTemplate().load(AttributeOption.class, id);
		
	}

	public boolean updateAttributeOption(AttributeOption option) {
		getHibernateTemplate().update(option);
		return false;
	}

	public int checkExist(String code) {
	Object obj = getSession().createQuery("select id from Product where code =?").setParameter(0, code).uniqueResult();
	if(obj==null){
		return 0;
	}else{
		int exist = ((Integer)obj).intValue();
		System.out.println(exist);
		return exist;
	}
	
	}
	public int checkExist(int id) {
		Object obj = getSession().createQuery("select id from Product where id =?").setParameter(0, id).uniqueResult();
		if(obj==null){
			return 0;
		}else{
			int exist = ((Integer)obj).intValue();
			System.out.println(exist);
			return exist;
		}
		
		}
	public Product findProduct(String code) {
		return (Product) getSession().createQuery("from Product where code =?").setParameter(0, code.trim()).uniqueResult();
		//return null;
	}

	public boolean checkImage(int productId, String imageUrl) {

		Object obj = getSession().createQuery("select id from ImagesAndStyle where product.id=? and imageUrl=?")
			.setParameter(0, productId).setParameter(1, imageUrl).uniqueResult();
		if(obj==null){
			return false;
		}else{
			return true;
		}
	
	}

	public boolean updateQuantity(int productId, int quantity) {
		
		getSession().createQuery("update Product set quantity=? where id=?").setParameter(0, quantity)
		.setParameter(1, productId).executeUpdate();
		return true;
	}

	public int updateCheckExistAttribute(String attributeName) {
		Object obj = getSession().createQuery("select id from ProductAttribute where name =?").setParameter(0, attributeName).uniqueResult();
		if(obj==null){
		//	return 0;
			ProductAttribute att = new ProductAttribute();
			att.setName(attributeName);
			getHibernateTemplate().save(att);
			return att.getId();
			
		}else{
			int exist = ((Integer)obj).intValue();
			//System.out.println(exist);
			return exist;
		}
	}

	public int checkExistAttributeOption(String option,int productId,int attributeId) {

		Object obj =getSession().createQuery("from AttributeOption where value=? and product.id=? and attribute.id=?")
		.setParameter(0, option).setParameter(1, productId)
		.setParameter(2, attributeId).uniqueResult();
		if(obj==null){
			return 0;
		}else{
			int exist = ((Integer)obj).intValue();
			//System.out.println(exist);
			return exist;
		}
	}

	public List<Product> getProductsByIds(Integer[] ids) {
		if(ids == null){
			return null;
			//throw new SystemException("非法操作！");
		}
		StringBuffer s = new StringBuffer();
		for(int i=0; i<ids.length-1;i++){
			s.append(ids[i]);
			s.append(",");
		}
		s.append(ids[ids.length-1]);
		return getSession().createQuery("from Product where id in (?)")
					.setParameter(0, s).list();
		
	}

	public Product getProductOnlyNameAndCategory(int id) {
		return (Product)getSession().createQuery("from Product product where product.id=?").setParameter(0, id).uniqueResult();
		
	}

	public void disableProduct(int id) {
		Product p = (Product)getHibernateTemplate().load(Product.class, id);
		if(p==null)	return;
		deleteProductFile(p.getShtml_File_Name());
		getSession().createQuery("update Product set visible=false where id=?")
		.setParameter(0, id)
		.executeUpdate();
		
	}

	public void disableProducts(int[] id) {
		StringBuffer ids = new StringBuffer();
		for(int i:id){
			Product p = (Product)getHibernateTemplate().load(Product.class, id);
			if(p==null)	continue;
			deleteProductFile(p.getShtml_File_Name());
			ids.append(i+",");
		}
		if(ids.length()>0)
		if(ids.charAt(ids.length()-1)==",".charAt(0)){
			ids.deleteCharAt(ids.length()-1);
		}
		getSession().createQuery("update Product set visible=false where id in(?)")
		.setParameter(0, ids.toString())
		.executeUpdate();
	}

	public void enableProduct(int id) {
		getSession().createQuery("update Product set visible=true where id=?")
		.setParameter(0, id)
		.executeUpdate();
		
	}

	public void enableProducts(int[] id) {
		StringBuffer ids = new StringBuffer();
		for(int i:id){
			ids.append(i+",");
		}
		if(ids.length()>0)
		if(ids.charAt(ids.length()-1)==",".charAt(0)){
			ids.deleteCharAt(ids.length()-1);
		}
		getSession().createQuery("update Product set visible=true where id in(?)")
		.setParameter(0, ids.toString())
		.executeUpdate();
		
	}

	public Product getProductMeta(int id) {
		return (Product)getSession().createQuery("select new Product(id,meta_Description,meta_KeyWords,name,shtml_File_Name,titleInPage) from Product where id=?")
		.setParameter(0, id).uniqueResult();
	}

	public void updateProductMeta(Product p) {
		getSession().createQuery("update Product set meta_Description=?,meta_KeyWords=?,name=?,shtml_File_Name=?,titleInPage=? where id=?")
		.setParameter(0, p.getMeta_Description())
		.setParameter(1, p.getMeta_KeyWords())
		.setParameter(2, p.getName())
		.setParameter(3, p.getShtml_File_Name())
		.setParameter(4, p.getTitleInPage())
		.setParameter(5, p.getId())
		.executeUpdate();
	}

	public void updateProductMeta(int id, String meta_Description,
			String meta_KeyWords, String name, String shtml_File_Name,
			String titleInPage) {
		getSession().createQuery("update Product set meta_Description=?,meta_KeyWords=?,name=?,shtml_File_Name=?,titleInPage=? where id=?")
		.setParameter(0, meta_Description)
		.setParameter(1, meta_KeyWords)
		.setParameter(2, name)
		.setParameter(3, shtml_File_Name)
		.setParameter(4, titleInPage)
		.setParameter(5, id)
		.executeUpdate();
		
	}

	public List<Product> getProductsByIdString(String ids) {
		if(ids==null||ids.trim().length()==0)	return null;
		return getSession().createQuery("from Product where id in ("+ids+")")
		.list();
	}
	public void updateall(){
		List<Product> datas = getSession().createQuery("select new Product(id,name,shtml_File_Name) from Product").list();
		
		for(Product p:datas){
			if(p.getName()==null||p.getName().trim().length()==0)	continue;
			//p.setShtml_File_Name(WebUtil.generateURL(p.getName()));
			getSession().createQuery("update Product p set p.shtml_File_Name=? where p.id=?")
			.setParameter(0, WebUtil.generateURL(ChineseToPinyinUtil.getFullSpell(p.getName())))
			.setParameter(1, p.getId())
			.executeUpdate();
		}
	
	}

	public void addProductDetailAttribute(ProductDetailAttribute attribute) {
		getHibernateTemplate().save(attribute);
		
	}

	public void addProductDetailGroup(ProductDetailGroup group) {
		getHibernateTemplate().save(group);
		
	}

	public void deleteProductDetailAttribute(int id) {
		getSession().createQuery("delete from ProductDetailAttribute att where att.id=?")
					.setParameter(0, id).executeUpdate();
		
	}

	public void deleteProductDetailAttributes(String ids) {
		getSession().createQuery("delete from ProductDetailAttribute att where att.id in(?)")
					.setParameter(0, ids).executeUpdate();
	}

	public void deleteProductDetailGroup(int groupId) {
		getSession().createQuery("delete from ProductDetailGroup g where g.id=?")
		.setParameter(0, groupId).executeUpdate();
		
	}

	public void deleteProductDetailGroups(String groupIds) {
		getSession().createQuery("delete from ProductDetailGroup g where g.id in(?)")
		.setParameter(0, groupIds).executeUpdate();
		
	}

	public List<ProductDetailGroup> getAllProductDetailGroups() {
		return (List<ProductDetailGroup>)getSession().createQuery("from ProductDetailGroup").list();
	
	}

	public ProductDetailAttribute getProductDetailAttribute(int attributeId) {
		return	(ProductDetailAttribute)getHibernateTemplate().get(ProductDetailAttribute.class, attributeId);
		
	}

	public List<ProductDetailAttribute> getProductDetailAttributes(String attributeIds) {
		return (List<ProductDetailAttribute>)getSession().createQuery("from ProductDetailAttribute att where att.id in(?)")
		.setParameter(0, attributeIds).list();
	}


	public ProductDetailGroup getProductDetailGroup(int groupId) {
		return (ProductDetailGroup)getHibernateTemplate().get(ProductDetailGroup.class, groupId);
	}



	public void updateProductDetailAttribute(ProductDetailAttribute attribute) {
		getHibernateTemplate().update(attribute);
		
	}

	public void updateProductDetailGroup(ProductDetailGroup group) {
		getHibernateTemplate().update(group);
		
	}

	public void addProductDetailOption(ProductDetailOption option) {
		getHibernateTemplate().save(option);
		
		
	}

	public void deleteProductDetailOption(int id) {
		getSession().createQuery("delete from ProductDetailOption o where o.id=?")
					.setParameter(0, id).executeUpdate();
		
	}

	public ProductDetailOption getProductDetailOption(int optionId) {
		return (ProductDetailOption)getHibernateTemplate().get(ProductDetailOption.class, optionId);

	}

	public List<ProductDetailOption> getProductDetailOptions(String optionIds) {
		return(List<ProductDetailOption>)getSession().createQuery("from ProductDetailOption o where o.id in(?)")
		.setParameter(0, optionIds).list();
		
	}

	public void updateProductDetailOption(ProductDetailOption option) {
		getHibernateTemplate().update(option);
		
	}
}
