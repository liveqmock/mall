package com.hnfealean.sport.model.deliver;
/**
 * @hibernate.class table="t_delivermodule"
 * @author Administrator
 *
 */
public class DeliverModule {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.many-to-one cascade="save-update"
	 */
	private DeliverType deliverType;
	
	/**
	 * @hibernate.property length="20"
	 */
	private String zoneName;

	/**
	 * @hibernate.property
	 */
	private float baseFee;

	/**
	 * @hibernate.property
	 */
	
	private float oneMoreFee;

	/**
	 * @hibernate.many-to-one  class="com.hnfealean.sport.model.deliver.DistributionTemplate" column="distributionId" 	
	 */
	private DistributionTemplate template;
	public DistributionTemplate getTemplate() {
		return template;
	}

	public void setTemplate(DistributionTemplate template) {
		this.template = template;
	}

	public DeliverType getDeliverType() {
		return deliverType;
	}

	public void setDeliverType(DeliverType deliverType) {
		this.deliverType = deliverType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public float getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(float baseFee) {
		this.baseFee = baseFee;
	}

	public float getOneMoreFee() {
		return oneMoreFee;
	}

	public void setOneMoreFee(float oneMoreFee) {
		this.oneMoreFee = oneMoreFee;
	}
}
