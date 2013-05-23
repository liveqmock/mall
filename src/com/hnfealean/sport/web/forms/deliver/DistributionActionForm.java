package com.hnfealean.sport.web.forms.deliver;

import org.apache.struts.action.ActionForm;

public class DistributionActionForm extends ActionForm {

	private int id;
	private String distributionTemplateName;
	private String distributionTemplateDescription;
	private String deliverTypeName;
	
	private int deliverModuleId;
	private int productId;
	
	private int distributionTemplateId;
	private int deliverTypeId;

	private int[] deliverTypeIds;
	private float deliverModuleBaseFee;
	private float deliverModuleOneMoreFee;
	private String zoneName;
	private String[] zone;
	
	private float[] baseFee;
	
	private float[] oneMoreFee;
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public float getDeliverModuleBaseFee() {
		return deliverModuleBaseFee;
	}

	public void setDeliverModuleBaseFee(float deliverModuleBaseFee) {
		this.deliverModuleBaseFee = deliverModuleBaseFee;
	}

	public float getDeliverModuleOneMoreFee() {
		return deliverModuleOneMoreFee;
	}

	public void setDeliverModuleOneMoreFee(float deliverModuleOneMoreFee) {
		this.deliverModuleOneMoreFee = deliverModuleOneMoreFee;
	}

	public int getDeliverModuleId() {
		return deliverModuleId;
	}

	public void setDeliverModuleId(int deliverModuleId) {
		this.deliverModuleId = deliverModuleId;
	}

	public int getDistributionTemplateId() {
		return distributionTemplateId;
	}

	public void setDistributionTemplateId(int deliverTemplateId) {
		this.distributionTemplateId = deliverTemplateId;
	}

	public String getDistributionTemplateDescription() {
		return distributionTemplateDescription;
	}

	public void setDistributionTemplateDescription(
			String distributionTemplateDescription) {
		this.distributionTemplateDescription = distributionTemplateDescription;
	}

	public String getDistributionTemplateName() {
		return distributionTemplateName;
	}

	public void setDistributionTemplateName(String distributionTemplateName) {
		this.distributionTemplateName = distributionTemplateName;
	}

	public int[] getDeliverTypeIds() {
		return deliverTypeIds;
	}

	public void setDeliverTypeIds(int[] deliverTypeIds) {
		this.deliverTypeIds = deliverTypeIds;
	}

	public String[] getZone() {
		return zone;
	}

	public void setZone(String[] zone) {
		this.zone = zone;
	}

	public float[] getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(float[] baseFee) {
		this.baseFee = baseFee;
	}

	public float[] getOneMoreFee() {
		return oneMoreFee;
	}

	public void setOneMoreFee(float[] oneMoreFee) {
		this.oneMoreFee = oneMoreFee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeliverTypeName() {
		return deliverTypeName;
	}

	public void setDeliverTypeName(String deliverTypeName) {
		this.deliverTypeName = deliverTypeName;
	}

	public int getDeliverTypeId() {
		return deliverTypeId;
	}

	public void setDeliverTypeId(int deliverTypeId) {
		this.deliverTypeId = deliverTypeId;
	}

}
