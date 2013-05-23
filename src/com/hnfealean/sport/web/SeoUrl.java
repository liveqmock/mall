package com.hnfealean.sport.web;

import java.util.List;

import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.model.product.Category;
import com.hnfealean.sport.model.product.Product;
import com.hnfealean.sport.pageset.PageModel;

public class SeoUrl {

	public static String createProductUrl(String productUrl,int id){
		if(WebProperty.readUrl("site.product.staticfile").equals("1")){
			//使用静态文件存储
		//	return WebProperty.readUrl("site.product.filefolder")+"/"+productUrl
		//	+WebProperty.readUrl("site.product.filesuffix");
			
			return "product/"+id;
		}else{
			
			return productUrl+WebProperty.readUrl("site.product.seosuffix");
		}
	}
	public static String createCategoryUrl(String categoryUrl,int id){
		if(WebProperty.readUrl("site.category.seo").equals("1")){
			return categoryUrl
			+WebProperty.readUrl("site.category.suffix");
		}
		return "category/"+id;
		//return "?id="+id;
	}
	public static PageModel createCategoryUrl(PageModel pm){
		for(Category category:(List<Category>)pm.getDatas()){
			category.setUrl(createCategoryUrl(category.getUrl(),category.getId()));
		}
		return pm;
	}
	public static PageModel createProductUrl(PageModel pm){
		for(Product product:(List<Product>)pm.getDatas()){
			product.setShtml_File_Name(createProductUrl(product.getShtml_File_Name(),product.getId()));
		}
		return pm;
	}
	public static List createCategoryUrl(List<Category> list){
		for(Category category:list){
			category.setUrl(createCategoryUrl(category.getUrl(),category.getId()));
		}
		return list;
	}
	public static List createProductUrl(List<Product> list){
		for(Product product:list){
			product.setShtml_File_Name(createProductUrl(product.getShtml_File_Name(),product.getId()));
		}
		return list;
	}
	public static String createArticleUrl(Article article,ArticleType type){
		return createArticleUrl(article,type.getName());
	}
        public static String createArticleUrl(Article article,String typeName){
        	if(WebProperty.readUrl("site.article.seo").equals("1")){
                        article.setSeod(true);
			return typeName+"/"+article.getUrl()+WebProperty.readUrl("site.article.seosuffix");
		}
                return "article/"+article.getId();
	}
}
