package com.hnfealean.sport.model.upload;

import java.util.Date;

/**
 * @hibernate.class table="t_upload"
 * @author Administrator
 *
 */
public class UploadFile {
	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}


	/**
	 * @hibernate.property 
	 */
	private Date createDate;
	
	/**
	 * @hibernate.property
	 */
	private String fileUrl;
	

}
