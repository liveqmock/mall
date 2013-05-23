package com.hnfealean.sport.model.email;

import org.apache.struts.action.ActionForm;

public class EmailSenderActionForm extends ActionForm {

	private int id;
	
	private int type; 
	
	private String receiver;
	
	private String[] reveivers;
	
	private String subject;
	
	private String content;
	
	private String mimeType;
	
	private int adminEmailId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String[] getReveivers() {
		return reveivers;
	}

	public void setReveivers(String[] reveivers) {
		this.reveivers = reveivers;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public int getAdminEmailId() {
		return adminEmailId;
	}

	public void setAdminEmailId(int adminEmailId) {
		this.adminEmailId = adminEmailId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
