package com.hnfealean.sport.web.forms.deliver;

import org.apache.struts.action.ActionForm;

public class GlobalDeliverModuleActionForm extends ActionForm{

	private int globalDistributionTemplateId;
	
	private String globalDistributionTemplateName;
	private float freeShippingOrderMinTotal;
	
	private float deliverFee;
	
	private int deliverTypeId;
	private int globalDeliverModuleId;
	private String zoneName;
	
	private int zoneId;
	
	public int getGlobalDeliverModuleId() {
		return globalDeliverModuleId;
	}

	public void setGlobalDeliverModuleId(int globalDeliverModuleId) {
		this.globalDeliverModuleId = globalDeliverModuleId;
	}

	public float getFreeShippingOrderMinTotal() {
		return freeShippingOrderMinTotal;
	}

	public void setFreeShippingOrderMinTotal(float freeShippingOrderMinTotal) {
		this.freeShippingOrderMinTotal = freeShippingOrderMinTotal;
	}

	public int getZoneId() {
		return zoneId;
	}

	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public float getDeliverFee() {
		return deliverFee;
	}

	public void setDeliverFee(float deliverFee) {
		this.deliverFee = deliverFee;
	}

	public int getDeliverTypeId() {
		return deliverTypeId;
	}

	public void setDeliverTypeId(int deliverTypeId) {
		this.deliverTypeId = deliverTypeId;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public int getGlobalDistributionTemplateId() {
		return globalDistributionTemplateId;
	}

	public void setGlobalDistributionTemplateId(int globalDistributionTemplateId) {
		this.globalDistributionTemplateId = globalDistributionTemplateId;
	}

	public String getGlobalDistributionTemplateName() {
		return globalDistributionTemplateName;
	}

	public void setGlobalDistributionTemplateName(
			String globalDistributionTemplateName) {
		this.globalDistributionTemplateName = globalDistributionTemplateName;
	}
	
}
