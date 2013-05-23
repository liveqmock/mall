package com.hnfealean.sport.model.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import com.hnfealean.sport.model.post.Posts;

/**
 * @hibernate.class
 * 	table="t_article"
 * @author Administrator
 *
 */

public class Article implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 154564564L;
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Article(int id) {
		super();
		this.id = id;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="60" not-null="true"
	 */
	private String name;
	
	/**
	 * @hibernate.property length="60"
	 */
	private String title;
	/**
	 * @hibernate.property length="60" unique="true"
	 */
	private String url;	
	/**
	 * @hibernate.property length="60"
	 */
	private String keywords;
	
	/**
	 * @hibernate.property length="120"
	 */
	private String description;
	
	/**
	 * @hibernate.many-to-one unique="true"
	 */
	private Posts content;
	
	/**
	 * 使用多对一
	 * @hibernate.many-to-one column="pid"
	 */
	private Article parent;
	
	/**
	 * @hibernate.property
	 */
	private Date date = new Date();
	/**
	 * @hibernate.many-to-one lazy="false"
	 */
	private ArticleType type;
	
	/**
	 * @hibernate.property
	 */
	private int categoryId;
	
	/**
	 * 
	 * @hibernate.set inverse="true" order-by="id asc"
	 * @hibernate.key column="pid"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.article.Article"	
	 */
	private Set<Article> children;
	
	/**
	 * @hibernate.property
	 */
	private String templateUrl="article.vm";
	/**
	 * @hibernate.property
	 */
	private boolean commentPermission=true;

        private boolean seod;

        public boolean getSeod() {
            return seod;
        }

        public void setSeod(boolean seod) {
            this.seod = seod;
        }

	public boolean getCommentPermission() {
		return commentPermission;
	}
	public void setCommentPermission(boolean commentPermission) {
		this.commentPermission = commentPermission;
	}
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	@SearchableId
	public int getId() {
		return id;
	}
	@SearchableComponent(refAlias="ArticleType")
	public ArticleType getType() {
		return type;
	}
	public void setType(ArticleType type) {
		this.type = type;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@SearchableProperty(store=Store.YES)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@SearchableProperty(store=Store.YES)
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	@SearchableProperty(store=Store.YES)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Posts getContent() {
		return content;
	}

	public void setContent(Posts content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Article getParent() {
		return parent;
	}

	public void setParent(Article parent) {
		this.parent = parent;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@SearchableProperty(store=Store.YES)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Article> getChildren() {
		return children;
	}
	public void setChildren(Set<Article> children) {
		this.children = children;
	}

}
