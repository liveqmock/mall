package com.hnfealean.sport.model.product;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

/**
 * @hibernate.class table="t_category"
 * @author Administrator
 *
 */
@Searchable(root = false)
public class Category  implements Serializable {
	public Category(Category parent, int id, String imageUrl, String name,
			String url) {
		super();
		this.parent = parent;
		this.id = id;
		this.imageUrl = imageUrl;
		this.name = name;
		this.url = url;
	}
	
	public Category(String description, int id, String imageUrl,
			String meta_Description, String meta_KeyWords, String name,
			Category parent, String titleInPage4category, boolean visible,
			String url) {
		super();
		this.description = description;
		this.id = id;
		this.imageUrl = imageUrl;
		this.meta_Description = meta_Description;
		this.meta_KeyWords = meta_KeyWords;
		this.name = name;
		this.parent = parent;
		this.titleInPage4category = titleInPage4category;
		this.visible = visible;
		this.url = url;
	}

	public Category(String description, int id, String imageUrl, String name,
			String url) {
		super();
		this.description = description;
		this.id = id;
		this.imageUrl = imageUrl;
		this.name = name;
		this.url = url;
	}

	public Category(int id) {
		super();
		this.id = id;
	}

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Category(String name) {
		super();
		this.name = name;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -2224245318885153229L;

	/**
	 * @hibernate.set order-by="id asc" inverse="true"
	 * @hibernate.key column="parentId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.Category"	
	 */
	private Set children=new HashSet<Category>();
	
	/**
	 * @hibernate.property length="1000"
	 */
	private String description;

	
	/**
	 * @hibernate.id 
	 * 		generator-class="native"
	 */

	private int id;

	/**
	 * @hibernate.property length="255"
	 */
	private String imageUrl;
	
	/**
	 * @hibernate.property length="255"
	 */
	private String meta_Description;

	/**
	 * @hibernate.property length="255"
	 */
	private String meta_KeyWords;
	
	/**
	 * @hibernate.property not-null="true" unique="true" length="120"
	 */
	private String name;
	
	/**
	 * @hibernate.many-to-one column="parentId"
	 */
	private Category parent;

	/**
	 * @hibernate.property length="120"
	 */
	private String titleInPage4category;

	/**
	 * @hibernate.property
	 */	
	private boolean visible;
	/**
	 * @hibernate.property length="120"
	 */
	private String url;
	/**
	 * @hibernate.property
	 */
	private int orderNo;

	
	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Set getChildren() {
		return children;
	}

	public String getDescription() {
		return description;
	}
	
	@SearchableId
	public int getId() {
		return id;
	}

	public String getImageUrl() {
		return imageUrl;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getMeta_Description() {
		return meta_Description;
	}

	public String getMeta_KeyWords() {
		return meta_KeyWords;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getName() {
		return name;
	}

	public Category getParent() {
		return parent;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getTitleInPage4category() {
		return titleInPage4category;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setChildren(Set children) {
		this.children = children;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImageUrl(String imageUrl) {
		if(imageUrl!=null&&imageUrl.length()>255){
			this.imageUrl=imageUrl.substring(0,255);
			return;
		}
		this.imageUrl = imageUrl;
	}

	public void setMeta_Description(String meta_Description) {
		if(meta_Description!=null&&meta_Description.length()>1000){
			this.meta_Description=meta_Description.substring(0,1000);
			return;
		}
		this.meta_Description = meta_Description;
	}

	public void setMeta_KeyWords(String meta_KeyWords) {
		if(meta_KeyWords!=null&&meta_KeyWords.length()>255){
			this.meta_KeyWords=meta_KeyWords.substring(0,255);
			return;
		}
		this.meta_KeyWords = meta_KeyWords;
	}

	public void setName(String name) {
		if(name!=null&&name.length()>120){
			this.name=name.substring(0,120);
			return;
		}
		this.name = name;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public void setTitleInPage4category(String titleInPage4category) {
		if(titleInPage4category!=null&&titleInPage4category.length()>120){
			this.titleInPage4category=titleInPage4category.substring(0,120);
			return;
		}
		this.titleInPage4category = titleInPage4category;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


}
