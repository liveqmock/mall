package com.hnfealean.sport.managers.impl.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.compass.core.CompassCallback;
import org.compass.core.CompassException;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;

import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.ImagesAndStyle;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SeoUrl;


public class ProductSearchResultCallBack extends CommonManager implements CompassCallback {

	private String key;
	//private int offset=0;//从第几条记录开始查询
	private int pagesize=10;//每页显示多少行
	private int page=0;
	
	public ProductSearchResultCallBack() {
	}

	public ProductSearchResultCallBack(String key, int page, int pagesize) {
		this.key = key;
		this.page = page;
		this.pagesize = pagesize;
	}


	public PageModel doInCompass(CompassSession session) throws CompassException {
		//IndexReader.open()
		List<Product> products = new ArrayList<Product>();

	//	查询方式：采用compassQueryBuilder，许多查询可以直接工作在mapping的层次上。
		CompassQueryBuilder queryBuilder = session.queryBuilder();//.createQueryBuilder();
	//	CompassHits hits = queryBuilder.term("a.familyName.family-name", "london").hits();
		// 采用类属性的元数据id, 在上面的例子中将采用第一个元数据.
	//	CompassHits hits = queryBuilder.term("a.familyName", "london").hits();
		//查询编辑器将会采用相应的转化器转换数据。
	//	CompassHits hits = queryBuilder.term("a.date.date-sem", new Date()).hits();
		 
	      CompassQuery allpropertyQuery = queryBuilder.queryString(key).toQuery();
	    //  CompassHits hits = allpropertyQuery.hits();
	  	CompassHits hits =  allpropertyQuery.setAliases("Product").hits();
//		CompassHits hits = queryBuilder.bool()
//		
//		.addMust( queryBuilder.alias("Product") )
//		//.addMust( queryBuilder.term("Product.name", "ugg") )
//		.toQuery().hits();
	
	//	CompassHits hits = session.find(key);
		int length = hits.length();
		System.out.println("hits.length()"+length);

		/*
		String m = h.hit(0).toString();
		
		
		CompassHit hit =h.hit(0);
		String sss = hit.getData().toString();
		//hits[0];//new Hit(hits, 0);
		System.out.println("session.find(key).getQuery().toString():"+session.find(key).getQuery().toString());
		System.out.println("hits.length()"+hits.length());
		//int lastIndex = pagesize - 1;
	
		Iterator ite =hits.iterator();
		while(ite.hasNext()){
			Object obj = ite.next();
			
		}*/
	//	if(lastIndex>(hits.length()-1)) lastIndex = hits.length()-1;
		
		if(page==0)	page=1;
		
		if(length>=page*pagesize){
			for(int i=(page-1)*pagesize;i<page*pagesize;i++){
				Product product = (Product) hits.data(i);
				System.out.println("第"+i+"条："+i);
				if(!products.contains(product))products.add(product);
			}
		}else if(length>(page-1)*pagesize&&length<=page*pagesize){
			for(int i=(page-1)*pagesize;i<length;i++){
				Product product = (Product) hits.data(i);
				System.out.println("第"+i+"条："+i);
				if(!products.contains(product))products.add(product);
			}
		}else if(length<=(page-1)*pagesize){
			return new PageModel();
		}
		PageModel pm  = new PageModel();
		for(Product p:products){
			p.setShtml_File_Name(SeoUrl.createProductUrl(p.getShtml_File_Name(),p.getId()));
			//p.setTempImageUrl(tempImageUrl)
			if(p.getCategory()!=null){
				Category category = p.getCategory();
				category.setUrl(SeoUrl.createCategoryUrl(category.getUrl(),category.getId()));
				
			}
			if(p.getImagesAndStyles()!=null&&p.getImagesAndStyles().size()>0){
				Set<ImagesAndStyle> images = p.getImagesAndStyles();
				for(ImagesAndStyle image:images){
					p.setTempImageUrl(image.getImageUrl());
					break;
				}
			}
		}
		pm.setDatas(products);//设置查询的结果
		pm.setTotal(hits.length());//设置查询到的总记录数
		return pm;
/*		for(int i=this.offset; i<this.offset+this.pagesize; i++){
			System.out.println("第"+i+"条："+i);
			if(this.offset+i<length){
				
				String s = 	hits.hit(this.offset+i).getAlias();
				//System.out.println("hits.hit(i).getAlias():"+hits.hit(i).getAlias());
				System.out.println("hits.hit(i).getClass():"+hits.hit(this.offset+i).getClass());
				if(hits.hit(this.offset+i).getAlias().equals("Product")){
				Product product = (Product) hits.data(this.offset+i);

				System.out.println("id:"+product.getId());
			//	hits.hit(i).getData().getClass();
				if(!products.contains(product)){
					if(hits.highlighter(i).fragment("name")!=null){//处理高亮显示
						//product.setName(hits.highlighter(i).fragment("name"));
					}
						products.add(product);
				}
			}
			}
		}*/

	}

}
