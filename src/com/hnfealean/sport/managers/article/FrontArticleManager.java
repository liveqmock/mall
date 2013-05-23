package com.hnfealean.sport.managers.article;


import java.util.List;

import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.pageset.PageModel;

public interface FrontArticleManager {
	public PageModel searchAllCategoryArticle(String type,Integer categoryId);
	public PageModel searchAllArticle(String Type,String pUrl);
	public PageModel searchArticleBypUrl(String url);
	public int getCategoryId(String url) ;
	public Article getArticle(Integer type,Integer id);
	public Article getArticle(Integer id);
	public PageModel searchAllCategoryArticle(Integer categoryId);
	public List searchAllCategoryArticle(Integer categoryId,Integer pagesize);
	public PageModel searchArticleAll(String typeName);
	public PageModel searchArticleAll(String typeName,Integer id);
	public ArticleType getArticleType(Integer typeId);
	public ArticleType getArticleType(String typeName);
	public List<Article> getCrumbs(Article article);
	public List<Article> getTopArticles(String type);
	public Article getArticleByUrlAndType(String type, String url);
}
