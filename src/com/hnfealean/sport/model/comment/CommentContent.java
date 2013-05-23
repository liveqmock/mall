package com.hnfealean.sport.model.comment;

import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

/**
 *  @hibernate.class table="t_comment_content"
 * @author Administrator
 *
 */
@Searchable(root = false)
public class CommentContent {

	/**
	 *  @hibernate.id generator-class="native"
	 */
	public int id;
	/**
	 * @hibernate.property length="300"
	 */
	public String content;
	@SearchableId
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@SearchableProperty(store=Store.YES)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
