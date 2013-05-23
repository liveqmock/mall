package com.hnfealean.sport.model.deliver;
/**
 * @hibernate.class table="t_delivertype"
 * @author Administrator
 *
 */
public class DeliverType {
	public DeliverType() {
		super();
	}
	public DeliverType(int id) {
		super();
		this.id = id;
	}
	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	private float fee;
	/**
	 * @hibernate.property length="50" not-null="true"
	 */
	private String name;
	/**
	 * @hibernate.property length="255"
	 */
	private String description;
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
		this.name = name;
	}

}
