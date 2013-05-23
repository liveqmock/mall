package com.hnfealean.sport.managers.article;

import java.util.List;

import com.hnfealean.sport.model.article.Article;
import com.hnfealean.sport.model.article.ArticleType;
import com.hnfealean.sport.pageset.PageModel;

public interface ArticleManager {

	//添加article
	public void addArticle(Article article);
	//根据ID删除article
	public boolean deleteArticleById(int id);
	//根据ID组删除article
	public boolean deleteArticlesByIds(int[] ids);	
	//更新article
	public void updateArticle(Article ArticleNow,int id);
	//搜索article，返回list数组
	public List searchArticle(String likeString);
	//搜索article，返回PageModel
	public PageModel searchArticleAll(int type);
	public PageModel searchArticleAll(String typeName);	
	public PageModel searchArticleAll(int type,int pId);
	public PageModel searchCategoryArticleAll(int type, int categoryId) ;
	//根据ID和类型获得article
	public Article getArticleById(int id,int type);
	//根据ID和类型检测指定Article是否存在
	public boolean checkArticleExists(int id,int type);
	//根据article的id和类型，获取相关文章
	public List<Article> getRelated(int id,int type);
	public int getpId(int id);
	
	public Article getArticleById(int id);
	
	//添加articleType
	public void addArticleType(ArticleType type);
	
	public void deleterArticleType(int articleTypeId);
	
	public void updateArticleTye(ArticleType type);
	
	public ArticleType getArticleType(int articleTypeId);
	
	public List<ArticleType> getAllArticleTypes();
}
