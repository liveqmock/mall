package com.hnfealean.sport.web.forms.product;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ProductActionForm extends ActionForm {
	private int id;
	private int[] ids;
	private static final long serialVersionUID = -933565151103304488L;

	private float basePrice;

	private String brandId;
	
	private String additionInfo;

	private int categoryId;
	
	private String code;
	
	private boolean commend = true;

	private String detail;

	private float endBasePrice;

	private float endSellPrice;

	private int imagesAndStylesId;

	private int[] imagesAndStylesIds;

	private int manufacturersId;

	private float marketPrice;
	private int attributeOptionId;
	private String attributeOptionValue;
	
	/**
	 * 
	 * 在网页上显示meta描述
	 */	
	private String meta_Description;

	/**
	 * 
	 * 在网页上显示meta关键词
	 */
	private String meta_KeyWords;

	private String model;

	private String name;

	private int productId;

	private int[] productIds;

	private FormFile productImagesAndStylesImageFile;
	
	private ArrayList productImagesAndStylesImageFiles = new ArrayList();

	private String productImagesAndStylesName;

	private int quantity;

	private float sellPrice;
	
	private String sex;

	/**
	 *
	 * 产品的shtml文件名
	 */
	private String shtml_File_Name;

	private float startBasePrice;

	private float startSellPrice;

	private String titleInPage;

	private boolean visible = true;

	private int weight;

	private boolean YesOrNo = true;
	
	private Date createDate;
	private int attributeId;
	private int outputWidth;
	private int outputHeight;

	private String value;
	
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = new Date(new SimpleDateFormat("YYYY-MM-DD").format(new Date(createDate.toString())));
	}

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

	public int getOutputWidth() {
		return outputWidth;
	}

	public void setOutputWidth(int outputWidth) {
		this.outputWidth = outputWidth;
	}

	public int getOutputHeight() {
		return outputHeight;
	}

	public void setOutputHeight(int outputHeight) {
		this.outputHeight = outputHeight;
	}

	public int getAttributeId() {
		return attributeId;
	}

	public void setAttributeId(int attributeId) {
		this.attributeId = attributeId;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public String getBrandId() {
		return brandId;
	}


	public int getAttributeOptionId() {
		return attributeOptionId;
	}

	public void setAttributeOptionId(int attributeOptionId) {
		this.attributeOptionId = attributeOptionId;
	}


	public String getAttributeOptionValue() {
		return attributeOptionValue;
	}

	public void setAttributeOptionValue(String attributeOptionValue) {
		this.attributeOptionValue = attributeOptionValue;
	}

	public String getAdditionInfo() {
		return additionInfo;
	}

	public void setAdditionInfo(String additionInfo) {
		this.additionInfo = additionInfo;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getCode() {
		return code;
	}

	public String getDetail() {
		return detail;
	}

	public float getEndBasePrice() {
		return endBasePrice;
	}

	public float getEndSellPrice() {
		return endSellPrice;
	}

	public int getManufacturersId() {
		return manufacturersId;
	}

	public float getMarketPrice() {
		return marketPrice;
	}

	public String getMeta_Description() {
		return meta_Description;
	}

	public String getMeta_KeyWords() {
		return meta_KeyWords;
	}

	public String getModel() {
		return model;
	}

	public String getName() {
		return name;
	}

	public int getProductId() {
		return productId;
	}

	public int[] getProductIds() {
		return productIds;
	}


	public int getQuantity() {
		return quantity;
	}

	public float getSellPrice() {
		return sellPrice;
	}

	public String getSex() {
		return sex;
	}

	public String getShtml_File_Name() {
		return shtml_File_Name;
	}

	public float getStartBasePrice() {
		return startBasePrice;
	}

	public float getStartSellPrice() {
		return startSellPrice;
	}

	public String getTitleInPage() {
		return titleInPage;
	}

	public int getWeight() {
		return weight;
	}
	
	public boolean isCommend() {
		return commend;
	}

	public boolean isVisible() {
		return visible;
	}

	public boolean isYesOrNo() {
		return YesOrNo;
	}
	
	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}
	
	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}
	

	
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public void setCommend(boolean commend) {
		this.commend = commend;
	}
	
	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setEndBasePrice(float endBasePrice) {
		this.endBasePrice = endBasePrice;
	}

	public void setEndSellPrice(float endSellPrice) {
		this.endSellPrice = endSellPrice;
	}
	
	public void setManufacturersId(int manufacturersId) {
		this.manufacturersId = manufacturersId;
	}
	
	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}
	
	public void setMeta_Description(String meta_Description) {
		meta_Description=meta_Description.length()>255?meta_Description.substring(0, 254):meta_Description;
		this.meta_Description = meta_Description;
	}
	
	public void setMeta_KeyWords(String meta_KeyWords) {
		meta_KeyWords=meta_KeyWords.length()>255?meta_KeyWords.substring(0, 254):meta_KeyWords;
		this.meta_KeyWords = meta_KeyWords;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setName(String name) {
		name=name.length()>120?name.substring(0, 119):name;
		this.name = name;
	}
	
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void setProductIds(int[] productIds) {
		this.productIds = productIds;
	}

	public int getImagesAndStylesId() {
		return imagesAndStylesId;
	}

	public void setImagesAndStylesId(int imagesAndStylesId) {
		this.imagesAndStylesId = imagesAndStylesId;
	}

	public int[] getImagesAndStylesIds() {
		return imagesAndStylesIds;
	}

	public void setImagesAndStylesIds(int[] imagesAndStylesIds) {
		this.imagesAndStylesIds = imagesAndStylesIds;
	}

	public FormFile getProductImagesAndStylesImageFile() {
		return productImagesAndStylesImageFile;
	}

	public void setProductImagesAndStylesImageFile(
			FormFile productImagesAndStylesImageFile) {
		this.productImagesAndStylesImageFile = productImagesAndStylesImageFile;
	}

	public String getProductImagesAndStylesName() {
		return productImagesAndStylesName;
	}

	public void setProductImagesAndStylesName(String productImagesAndStylesName) {
		this.productImagesAndStylesName = productImagesAndStylesName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setShtml_File_Name(String shtml_File_Name) {
		shtml_File_Name=shtml_File_Name.length()>120?shtml_File_Name.substring(0, 119):shtml_File_Name;
		this.shtml_File_Name = shtml_File_Name;
	}

	public void setStartBasePrice(float startBasePrice) {
		this.startBasePrice = startBasePrice;
	}

	public void setStartSellPrice(float startSellPrice) {
		this.startSellPrice = startSellPrice;
	}

	public void setTitleInPage(String titleInPage) {
		titleInPage= titleInPage.length()>120?titleInPage.substring(0,119):titleInPage;
		this.titleInPage = titleInPage;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setYesOrNo(boolean YesOrNo) {
		this.YesOrNo = YesOrNo;
	}

	public ArrayList getProductImagesAndStylesImageFiles() {
		return productImagesAndStylesImageFiles;
	}

	public void setProductImagesAndStylesImageFiles(
			ArrayList productImagesAndStylesImageFiles) {
		this.productImagesAndStylesImageFiles = productImagesAndStylesImageFiles;
	}
}
