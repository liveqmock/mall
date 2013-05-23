package com.hnfealean.sport.managers.impl.article;

import java.util.ArrayList;
import java.util.List;

import com.hnfealean.sport.enums.ConstantString;
import com.hnfealean.sport.managers.article.FrontArticleManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.pageset.PageModel;

public class FrontArticlemanagerImpl extends CommonManager implements FrontArticleManager {
	public PageModel searchArticleBypUrl(String url) {
		
			return searchPaginated("from Article where parent.url=?",url);
	
	}
	public PageModel searchAllArticle(String type,String pUrl) {
		if(pUrl==null||pUrl.trim().length()==0){
			if(type.toLowerCase().equals(ConstantString.NEWS)){
				return searchPaginated("from Article where type="+ConstantString.NEWSVALUE +" and parent is null");
			}else if(type.toLowerCase().equals(ConstantString.BLOG)){
				return searchPaginated("from Article where type="+ConstantString.BLOGVALUE +" and parent is null");
			}else if(type.toLowerCase().equals(ConstantString.WIKI)){
				return searchPaginated("from Article where type="+ConstantString.WIKIVALUE +" and parent is null");
			}else if(type.toLowerCase().equals(ConstantString.THEME)){
				return searchPaginated("from Article where type="+ConstantString.THEMEVALUE +" and parent is null");
			}else if(type.toLowerCase().equals(ConstantString.HELPCENTER)){
				return searchPaginated("from Article where type="+ConstantString.HELPCENTERVALUE +" and parent is null");
			}else{
				return null;
			}
			//return null;
		}else{
			if(type.toLowerCase().equals(ConstantString.NEWS)){
				return searchPaginated("from Article where type="+ConstantString.NEWSVALUE +" and parent.url="+pUrl);
			}else if(type.toLowerCase().equals(ConstantString.NEWS)){
				return searchPaginated("from Article where type="+ConstantString.BLOGVALUE +" and parent.url="+pUrl);
			}
			return null;
		}
	}
	public Article getArticle(Integer type, Integer id) {

			Article article =(Article) getSession().createQuery("from Article where id=? and type=?")
						.setParameter(0, id)
						.setParameter(1, type).uniqueResult();

		return article;
	}
	public PageModel searchAllCategoryArticle(String type,Integer categoryId) {
		if(categoryId==0)return null;
		if(type.toLowerCase().equals(ConstantString.CATEGORY))
			return searchPaginated("from Article where type="+ConstantString.CATEGORYVALUE +" and categoryId="+categoryId);
		return null;
	}
	public PageModel searchAllCategoryArticle(Integer categoryId) {
		return searchPaginated("from Article where type="+ConstantString.CATEGORYVALUE +" and categoryId="+categoryId);
	
	}
	
	public int getCategoryId(String url) {
		
		try {
			return(Integer)getSession().createQuery("select id from Category where url=?").setParameter(0, url).uniqueResult();
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return 0;
	}
	public List searchAllCategoryArticle(Integer categoryId,Integer pagesize) {
		System.out.println(pagesize+categoryId);
		List<Article> articles =getSession().createQuery("from Article article where article.type.id=? and article.categoryId=? order by id desc")
		.setParameter(0, ConstantString.CATEGORYVALUE )
		.setParameter(1, categoryId)
		.setMaxResults(pagesize).list();
		return articles;
	
	}
	public PageModel searchArticleAll(String typeName) {
		return searchPaginated("from Article where type.name=? and parent is null",typeName);
	}
	public ArticleType getArticleType(Integer typeId) {
		return(ArticleType)getHibernateTemplate().get(ArticleType.class, typeId);
		
	}
	public ArticleType getArticleType(String typeName) {
	return (ArticleType)	getSession().createQuery("from ArticleType where name=?")
		.setParameter(0, typeName)
		.setMaxResults(1)
		.uniqueResult();

	}
	public PageModel searchArticleAll(String typeName, Integer id) {
		if(id==null||id==0){
			return searchPaginated("from Article where type.name=? and parent is null",typeName);
		}else{
			Article parent = (Article)getSession().createQuery("select article.parent from Article article where article.id=?")
			.setParameter(0, id)
			.setMaxResults(1)
			.uniqueResult();
			if(parent==null){
				return searchPaginated("from Article article where article.type.name=? and article.parent is null",new Object[]{typeName});
			}
			return searchPaginated("from Article article where article.type.name=? and article.parent.id=?)",new Object[]{typeName,parent.getId()});
		}

	}
	public Article getArticle(Integer id) {
		return (Article)getHibernateTemplate().get(Article.class, id);
	}
	public List<Article> getCrumbs(Article article) {
		List<Article> crumbs = new ArrayList<Article>();
		Article parent = article.getParent();
		while(parent!=null){
			crumbs.add(parent)	;
			parent=parent.getParent();
		}
		crumbs.add(article);
		return crumbs;
	}
	public List<Article> getTopArticles(String typeName) {
		return (List<Article>)getSession().createQuery("from Article article where article.type.name=? and article.parent is null")
					.setParameter(0, typeName).list();
	}
	public Article getArticleByUrlAndType(String type, String url) {
		return (Article)getSession().createQuery("from Article article where article.type.name=? and article.url=?")
		.setParameter(0, type).setParameter(1, url).setMaxResults(1).uniqueResult();
	}

}
