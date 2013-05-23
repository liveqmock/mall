package com.hnfealean.sport.model.article;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

/**
 * @hibernate.class table="t_articletype"
 * @author Administrator
 *
 */
@Searchable(root = false)
public class ArticleType {
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	/**
	 * @hibernate.property length="20"
	 */
	private String name;
	
	/**
	 * @hibernate.property
	 */
	private String templateUrl;
	/**
	 * @hibernate.property
	 */
	private String title;
	/**
	 * @hibernate.property
	 */
	private String keywords;
	/**
	 * @hibernate.property
	 */
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemplateUrl() {
		return templateUrl;
	}
	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl;
	}
	public ArticleType() {
		super();
	}
	@SearchableId
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArticleType(int id) {
		super();
		this.id = id;
	}	
}
