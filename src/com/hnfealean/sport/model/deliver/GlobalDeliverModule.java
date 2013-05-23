package com.hnfealean.sport.model.deliver;

/**
 * @hibernate.class table="t_globaldelivermodule"
 * @author Administrator
 *
 */
public class GlobalDeliverModule {

	
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;

	/**
	 * @hibernate.many-to-one
	 */
	private DeliverType deliverType;
	/**
	 * @hibernate.property
	 */
	private String zoneName;
	/**
	 * @hibernate.property 
	 * 免运费的最小订单总额度
	 */
	private float freeShippingOrderMinTotal;
	
	/**
	 * @hibernate.property
	 */
	private float deliverFee;

	/**
	 * @hibernate.many-to-one class="com.hnfealean.sport.model.deliver.GlobalDistributionTemplate" column="globalDistributionId"
	 */
	private GlobalDistributionTemplate globalTemplate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DeliverType getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(DeliverType deliverType) {
		this.deliverType = deliverType;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public float getFreeShippingOrderMinTotal() {
		return freeShippingOrderMinTotal;
	}

	public void setFreeShippingOrderMinTotal(float freeShippingOrderMinTotal) {
		this.freeShippingOrderMinTotal = freeShippingOrderMinTotal;
	}

	public float getDeliverFee() {
		return deliverFee;
	}

	public void setDeliverFee(float deliverFee) {
		this.deliverFee = deliverFee;
	}

	public GlobalDistributionTemplate getGlobalTemplate() {
		return globalTemplate;
	}

	public void setGlobalTemplate(GlobalDistributionTemplate globalTemplate) {
		this.globalTemplate = globalTemplate;
	}

	
}
