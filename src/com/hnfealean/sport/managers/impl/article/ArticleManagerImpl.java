package com.hnfealean.sport.managers.impl.article;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hnfealean.sport.managers.article.ArticleManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;

public class ArticleManagerImpl extends CommonManager implements ArticleManager {

	public void addArticle(Article article) {
	//	getHibernateTemplate().save(article.getContent());
		getHibernateTemplate().save(article);
	}

	public boolean deleteArticleById(int id) {
		
		getSession().createQuery("delete from Article where id=?")
			.setParameter(0, id).executeUpdate();
		return true;
	}

	public Article getArticleById(int id, int type) {
		
		Article article = (Article)getSession().createQuery("from Article where id=? and type.id=?")
					.setParameter(0, id).setParameter(1, type).uniqueResult();
		return article;
	}

	public List searchArticle(String likeString) {
		
		return null;
	}

	public PageModel searchArticleAll(int typeId) {
		/*
		getSession().createQuery("from Article where type=?").setParameter(0, type)
		.list();
		*/
		return searchPaginated("from Article where type.id=?",typeId);
	}

	public void updateArticle(Article ArticleNow, int id) {
		
		getHibernateTemplate().update(ArticleNow);
		
	}

	public PageModel searchArticleAll(int typeId, int pId) {
		if(pId==0)
			return searchPaginated("from Article where type.id=? and parent is null",typeId);
		else{
			/*Object obj[] = new Object[2] ;
		obj[0]=typeId;
		obj[1]=pId;*/
			return searchPaginated("from Article where type.id=? and parent.id=?",new Object[]{typeId,pId});
		}
	}
	public PageModel searchCategoryArticleAll(int typeId, int categoryId) {
		if(categoryId==0)
			return searchPaginated("from Article where type.id=? and categoryId=0",typeId);
		else{
		/*	Object obj[] = new Object[2] ;
		obj[0]=typeId;
		obj[1]=categoryId;*/
			return searchPaginated("from Article where type.id=? and categoryId=?",new Object[]{typeId,categoryId});
		}
	}
	public boolean deleteArticlesByIds(int[] ids) {
		if(ids == null){
			throw new SystemException("非法操作！");
		}
		StringBuffer s = new StringBuffer();
		for(int i=0; i<ids.length-1;i++){
			s.append(ids[i]);
			s.append(",");
		}
		s.append(ids[ids.length-1]);
		System.out.println("delete from Article where id in (?)"+ s.toString());
	//	getSession().createQuery("delete from Article where id in (?)").setParameter(0, s.toString())
		getSession().createQuery("delete from Article where id in ("+s.toString()+")")
			.executeUpdate();
		return false;
	}

	public List<Article> getRelated(int id, int typeId) {
		String keywords = (String)getSession().createQuery("select keywords from Article where id=? and type.id=?")
			.setParameter(0, id).setParameter(1, typeId).uniqueResult();
		if(keywords==null||keywords.trim().length()==0)
			return null;
		String[] keywordArray = keywords.split(",");
		System.out.println(keywordArray);
		StringBuffer keys = new StringBuffer();
		for(int i=0;i<keywordArray.length-1;i++){
			keys.append(keywordArray[i]);
		}
		keys.append(keywordArray[keywordArray.length-1]);
		List<Article> articles = new ArrayList<Article>();
		for(int i=0;i<keywordArray.length-1;i++){
			List<Article> temp = (List<Article>) getSession().createQuery("from Article where keywords like ?")
										.setParameter(0,"'%"+ keywordArray[i]+"%'").list();
			Iterator<Article> ite = temp.iterator();
			while(ite.hasNext()){
				Article aTemp = (Article) ite.next();
				if(!articles.contains(aTemp)&&aTemp.getId()!=id){
					articles.add(aTemp);
				}
			}
		}
		return articles;
	}

	public int getpId(int id) {
		if(id==0)	return 0;
		else{
		Object o =	getSession().createQuery("select parent.id from Article where id=?")
			.setParameter(0, id).uniqueResult();
		if(o==null)	return 0;
		Integer i;
		try{
			 i = (Integer) o;
		}catch(Exception e){
			return 0;
		}
			return i;
		}
	}

	public Article getArticleById(int id) {
		return (Article) getHibernateTemplate().get(Article.class, id);

	}

	public void addArticleType(ArticleType type) {
		getHibernateTemplate().save(type);
		
	}

	public void deleterArticleType(int id) {
		getSession().createQuery("delete from ArticleType where id=?").setParameter(0, id).executeUpdate();
		
	}

	public List<ArticleType> getAllArticleTypes() {
		return (List<ArticleType>) getSession().createQuery("from ArticleType").list();
		
	}

	public ArticleType getArticleType(int id) {
		return (ArticleType)getHibernateTemplate().get(ArticleType.class, id);
	}

	public void updateArticleTye(ArticleType type) {
		getHibernateTemplate().update(type);
	}

	public PageModel searchArticleAll(String typeName) {
		return searchPaginated("from Article where type.name=?",typeName);
	}

	public boolean checkArticleExists(int id, int type) {
		Integer i=0;
		Object obj = getSession().createQuery("select id from Article article where article.id=? and type=?")
					.setParameter(0, id)
					.setParameter(1, type)
					.uniqueResult();
		if(obj==null)return false;
		i=(Integer) obj;
		if(i>0)return true;
		return false;
	}

}
