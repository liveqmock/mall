package com.hnfealean.sport.model.product;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableComponent;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

import com.hnfealean.sport.model.deliver.DistributionTemplate;

/**
 * @hibernate.class table="t_product"
 * @author Administrator
 *
 */
@Searchable
public class Product implements Serializable {
	
	public Product(int id, String name, String shtml_File_Name) {
		super();
		this.id = id;
		this.name = name;
		this.shtml_File_Name = shtml_File_Name;
	}
	public Product(int id, String meta_Description, String meta_KeyWords,
			String name, String shtml_File_Name, String titleInPage) {
		super();
		this.id = id;
		this.meta_Description = meta_Description;
		this.meta_KeyWords = meta_KeyWords;
		this.name = name;
		this.shtml_File_Name = shtml_File_Name;
		this.titleInPage = titleInPage;
	}
	public Product(int id, String name,Category category,String filter) {
		super();
		this.category = category;
		this.id = id;
		this.name = name;
		this.filter = filter;
	}
	public Product(int id, String name,Category category) {
		super();
		this.category = category;
		this.id = id;
		this.name = name;
	}
	public Product(int id, float marketPrice, String name, float sellPrice,
			String shtml_File_Name) {
		super();
		this.id = id;
		this.marketPrice = marketPrice;
		this.name = name;
		this.sellPrice = sellPrice;
		this.shtml_File_Name = shtml_File_Name;
	}
	
	public Product(int id, float marketPrice, String meta_Description,
			String name, float sellPrice, String shtml_File_Name) {
		super();
		this.id = id;
		this.marketPrice = marketPrice;
		this.meta_Description = meta_Description;
		this.name = name;
		this.sellPrice = sellPrice;
		this.shtml_File_Name = shtml_File_Name;
	}
	public Product(float marketPrice, String name, float sellPrice,
			String shtml_File_Name) {
		super();
		this.marketPrice = marketPrice;
		this.name = name;
		this.sellPrice = sellPrice;
		this.shtml_File_Name = shtml_File_Name;
	}
	public Product() {
		super();
	}
	public Product(int id) {
		super();
		this.id=id;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -956613594711751264L;

	/** 
	 * @hibernate.property length="30"
	 *	 	购买说明 
	 */
	private String additionInfo;

	/** 
	 * @hibernate.property not-null="true"
	 *		 底价(采购进来的价格)
	 */

	private float basePrice=0f;

	/** 
	 * @hibernate.many-to-one column="brandId" class="com.hnfealean.sport.model.product.Brand"
	 * 		品牌 
	 */

	private Brand brand;
	
	/**
	 * @hibernate.many-to-one column="categoryId" not-null="true" class="com.hnfealean.sport.model.product.Category" 
	 *  产品类型
	 */

	private Category category;
	
	/** 
	 * @hibernate.property not-null="true"
	 * 人气指数
	 */
	private int clickCount = 1;
	
	/**
	 * @hibernate.property length="30" unique="true"
	 * 		货号
	 */
	private String code;
	
	/**
	 * @hibernate.property not-null="true"
	 *  是否推荐 
	 */
	private boolean commend = false;
	
	/**
	 * @hibernate.property
	 *  上架日期 
	 */
	private Date createDate = new Date();
	
	/** 
	 * @hibernate.property not-null="true" length="500"
	 *	 	产品简介
	 */
	private String detail;
	
	/**
	 * @hibernate.property
	 *  下架日期 
	 */
	private Date expireDate = new Date();
	
	/**
	 * @hibernate.id generator-class="native"
	 */

	private int id;
	
	//	private Sex sexrequest = Sex.NONE;
	/**
	 * @hibernate.set order-by="id asc" 
	 * @hibernate.key column="productId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.ImagesAndStyle"	
	 */
	
	private Set<ImagesAndStyle> imagesAndStyles =new HashSet<ImagesAndStyle>() ;
	
	/**
	 * @hibernate.set order-by="id asc" 
	 * @hibernate.key column="productId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.Manufacturer"	
	 */	
	private Set manufacturers=new HashSet();
	

	/**
	 * @hibernate.property not-null="true"
	 *  	市场价 
	 */
	private float marketPrice=0f;

	/**
	 * @hibernate.property length="1000"
	 * 在网页上显示meta描述
	 */	
	private String meta_Description;

	/**
	 * @hibernate.property length="255"
	 * 在网页上显示meta关键词
	 */
	private String meta_KeyWords;
	
	/** 
	 * @hibernate.property length="20"
	 * 		型号 
	 */
	private String model;
	
	/** 
	 * @hibernate.property length="120" not-null="true"
	 * 		产品名称 
	 */  
	private String name;
	/**
	 * 排序
	 */
	private int orderNo;
	
	/**
	 * 库存
	 *  @hibernate.property
	 */
	private int quantity;

	/**
	 * @hibernate.property not-null="true"
	 *  销售量
	 */
	private int sellCount = 0;

	/**
	 * @hibernate.property not-null="true"
	 * 		 销售价 
	 */
	private float sellPrice=0;

	/**
	 * @hibernate.property length="10" 
	 *  性别要求
	 */
	private String sex = "男女不限";

	/**
	 * @hibernate.property length="120"
	 * 产品的shtml文件名
	 */
	private String shtml_File_Name="";

	/**
	 * @hibernate.set order-by="id asc" 
	 * @hibernate.key column="productId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.Size"	
	 */
	
	private Set sizes = new HashSet();
	/**
	 * @hibernate.property length="255"
	 * 
	 */	
	private String filter;
	
	/**
	 * 一对多，在多的一端添加外键，指向一的一端的主键
	 * 下面的即是在t_attributeoption表中添加一个名为productId的字段,做为外键指向t_product表的主键
	 * @hibernate.set order-by="id asc"  inverse="false"
	 * @hibernate.key column="productId"
	
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.AttributeOption"	
	 */
	private Set<AttributeOption> options;
	/**
	 * 一对多，在多的一端添加外键，指向一的一端的主键
	 * 下面的即是在t_attributeoption表中添加一个名为productId的字段,做为外键指向t_product表的主键
	 * @hibernate.set order-by="id asc"
	 * @hibernate.key column="productId"
	 * @hibernate.one-to-many class="com.hnfealean.sport.model.product.ProductDetailGroup"	
	 */	
	private Set<ProductDetailGroup> groups;
	
	/**
	 * @hibernate.many-to-one column="distributionId"
	 */
	private DistributionTemplate distributionTemplate;
	
	private String tempImageUrl;
	

	public Set<ProductDetailGroup> getGroups() {
		return groups;
	}
	
	public String getTempImageUrl() {
		return tempImageUrl;
	}
	public void setTempImageUrl(String tempImageUrl) {
		this.tempImageUrl = tempImageUrl;
	}
	public DistributionTemplate getDistributionTemplate() {
		return distributionTemplate;
	}
	public void setDistributionTemplate(DistributionTemplate distributionTemplate) {
		this.distributionTemplate = distributionTemplate;
	}

	/**
	 * @hibernate.property length="255"
	 * 在网页上显示出来的标题
	 */
	private String titleInPage="";

	/** @hibernate.property not-null="true"
	 * 		是否可见 
	 */
	private boolean visible = true;

	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @hibernate.property
	 * 		 重量 单位:克 
	 */
	@SearchableProperty(index=Index.NO,store=Store.YES)
	private int weight;
	
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getAdditionInfo() {
		return additionInfo;
	}

	public Float getBasePrice() {
		return basePrice;
	}
	@SearchableComponent(refAlias="Brand")
	public Brand getBrand() {
		return brand;
	}


	@SearchableComponent(refAlias="Category")
	public Category getCategory() {
		return category;
	}

	public int getClickCount() {
		return clickCount;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getCode() {
		return code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getCreateDate() {
		return createDate;
	}
	@SearchableProperty(store=Store.YES)
	public String getDetail() {
		return detail;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	@SearchableId
	public int getId() {
		return id;
	}

	@SearchableComponent
	public Set<ImagesAndStyle> getImagesAndStyles() {
		return imagesAndStyles;
	}

	public Set getManufacturers() {
		return manufacturers;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public Float getMarketPrice() {
		return marketPrice;
	}
	@SearchableProperty(store=Store.YES)
	public String getMeta_Description() {
		return meta_Description;
	}
	@SearchableProperty(store=Store.YES)
	public String getMeta_KeyWords() {
		return meta_KeyWords;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public String getModel() {
		return model;
	}
	@SearchableProperty(boost=1,store=Store.YES)
	public String getName() {
		return name;
	}

	public int getSellCount() {
		return sellCount;
	}
	@SearchableProperty(index=Index.NO,store=Store.YES)
	public Float getSellPrice() {
		return sellPrice;
	}

	public String getSex() {
		return sex;
	}
	@SearchableProperty(store=Store.YES)
	public String getShtml_File_Name() {
		return shtml_File_Name;
	}

	public Set getSizes() {
		return sizes;
	}
	@SearchableProperty(boost=2,store=Store.YES)
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

	public void setAdditionInfo(String additionInfo) {
		this.additionInfo = additionInfo;
	}
	
	public void setBasePrice(Float basePrice) {
		this.basePrice = basePrice;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}
	
	public void setCode(String code) {
		if(code!=null&&code.length()>30){
			this.code=code.substring(0,30);
			return;
		}
		this.code = code;
	}
	
	public void setCommend(boolean commend) {
		this.commend = commend;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setImagesAndStyles(Set<ImagesAndStyle> imagesAndStyles) {
		this.imagesAndStyles = imagesAndStyles;
	}

	public void setManufacturers(Set manufacturers) {
		this.manufacturers = manufacturers;
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

	public void setModel(String model) {
		this.model = model;
	}

	public void setName(String name) {
		if(name!=null&&name.length()>120){
			this.name=name.substring(0,120);
			return;
		}
		this.name = name;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setShtml_File_Name(String shtml_File_Name) {
		if(shtml_File_Name!=null&&shtml_File_Name.length()>120){
			this.shtml_File_Name=shtml_File_Name.substring(0,120);
			return;
		}
		this.shtml_File_Name = shtml_File_Name;
	}

	public void setSizes(Set sizes) {
		this.sizes = sizes;
	}

	public void setTitleInPage(String titleInPage) {
		if(titleInPage!=null&&titleInPage.length()>255){
			this.titleInPage=titleInPage.substring(0,255);
			return;
		}
		this.titleInPage = titleInPage;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public void setBasePrice(float basePrice) {
		this.basePrice = basePrice;
	}

	public void setMarketPrice(float marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Set<AttributeOption> getOptions() {
		return options;
	}
	public void setOptions(Set<AttributeOption> options) {
		this.options = options;
	}
	public void setGroups(Set<ProductDetailGroup> groups) {
		this.groups = groups;
	}
}
