package com.hnfealean.sport.model.search;

/**
 * @hibernate.class table="t_searchoutput"
 * @author Administrator
 *
 */
public class SearchOutput {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="false" length="500"
	 */
	private String output;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
