package com.hnfealean.sport.web.forms.product;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class CategoryActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	//private String top10;
	//private String bestseller;
	//private String recommend;
	private String meta_Description;
	private String meta_KeyWords;
	private String titleInPage4category;
	private FormFile imageFile;
	private String imageUrl;
	private String url;
	private boolean visible = true;
	private int parentId;
	private int productId;
	private int[] categoryAddedProductList;
	private boolean addOrDel;
	private String addedType;
	private String productIds;
	private int orderNo;
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public boolean getAddOrDel() {
		return addOrDel;
	}
	public boolean isAddOrDel() {
		return addOrDel;
	}
	public void setAddOrDel(boolean addOrDel) {
		this.addOrDel = addOrDel;
	}
	public String getProductIds() {
		return productIds;
	}
	public void setProductIds(String productIds) {
		this.productIds = productIds;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int[] getCategoryAddedProductList() {
		return categoryAddedProductList;
	}
	
public String getAddedType() {
		return addedType;
	}
	public void setAddedType(String addedType) {
		this.addedType = addedType;
	}
	//	public String getTop10() {
//		return top10;
//	}
//	public void setTop10(String top10) {
//		this.top10 = top10;
//	}
//	public String getBestseller() {
//		return bestseller;
//	}
//	public void setBestseller(String bestseller) {
//		this.bestseller = bestseller;
//	}
//	public String getRecommend() {
//		return recommend;
//	}
//	public void setRecommend(String recommend) {
//		this.recommend = recommend;
//	}
	public void setCategoryAddedProductList(int[] categoryAddedProductList) {
		this.categoryAddedProductList = categoryAddedProductList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMeta_Description() {
		return meta_Description;
	}
	public void setMeta_Description(String meta_Description) {
		this.meta_Description = meta_Description;
	}
	public String getMeta_KeyWords() {
		return meta_KeyWords;
	}
	public void setMeta_KeyWords(String meta_KeyWords) {
		this.meta_KeyWords = meta_KeyWords;
	}
	public String getTitleInPage4category() {
		return titleInPage4category;
	}
	public void setTitleInPage4category(String titleInPage4category) {
		this.titleInPage4category = titleInPage4category;
	}
	public FormFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(FormFile imageFile) {
		this.imageFile = imageFile;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}


}
