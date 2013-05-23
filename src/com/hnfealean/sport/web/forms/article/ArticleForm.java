package com.hnfealean.sport.web.forms.article;

import java.util.Date;

import org.apache.struts.action.ActionForm;

public class ArticleForm extends ActionForm {


	private int id;
	
	private int[] ids;
	
	private String url;
	
	private String name;
	
	private String title;
	
	private String keywords;
	
	private String description;
	
	private String content;
	
	private int contentId;
	private int pId;
	private int categoryId;
	private int typeId;
	private String templateUrl;
	
	private boolean commentPermission;
	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public boolean getCommentPermission() {
		return commentPermission;
	}

	public void setCommentPermission(boolean commentPermission) {
		this.commentPermission = commentPermission;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getTemplateUrl() {
		return templateUrl;
	}

	public void setTemplateUrl(String templateUrl) {
		this.templateUrl = templateUrl.replace("..", "");
	}

	public int getPId() {
		return pId;
	}

	public void setPId(int id) {
		pId = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	private Date date = new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int[] getIds() {
		return ids;
	}

	public void setIds(int[] ids) {
		this.ids = ids;
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}

