package com.hnfealean.sport.web.forms.product;

import org.apache.struts.action.ActionForm;

public class CAttributeForm extends ActionForm {

	private int caId;
	
	private int caoptionId;
	private int[] caIds;
	private int[] caoptionIds;
	public int getCaId() {
		return caId;
	}
	public void setCaId(int caId) {
		this.caId = caId;
	}
	public int getCaoptionId() {
		return caoptionId;
	}
	public void setCaoptionId(int caoptionId) {
		this.caoptionId = caoptionId;
	}
	public int[] getCaIds() {
		return caIds;
	}
	public void setCaIds(int[] caIds) {
		this.caIds = caIds;
	}
	public int[] getCaoptionIds() {
		return caoptionIds;
	}
	public void setCaoptionIds(int[] caoptionIds) {
		this.caoptionIds = caoptionIds;
	}
}
