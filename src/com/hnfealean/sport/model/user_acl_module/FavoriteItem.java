package com.hnfealean.sport.model.user_acl_module;
/**
 * @hibernate.class table="t_favoriteitem"
 * @author Administrator
 *
 */
public class FavoriteItem {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="true" length="255"
	 */
	private String name;
	
	/**
	 * @hibernate.property not-null="true" length="255"
	 */
	private String url;
	
	/**
	 * @hibernate.property not-null="true" length="255"
	 */
	private String imageUrl;
	/**
	 * @hibernate.property length="20"
	 */
	private String remark;

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
		if(name!=null&&name.length()>255){
			this.name = name.substring(0, 255);
			return;
		}
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if(url!=null&&url.length()>255){
			this.url = url.substring(0, 255);
			return;
		}
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		if(imageUrl!=null&&imageUrl.length()>255){
			this.imageUrl = imageUrl.substring(0, 255);
			return;
		}
		this.imageUrl = imageUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		if(remark!=null&&remark.length()>20){
			this.remark = remark.substring(0, 20);
			return;
		}
		this.remark = remark;
	}


}
