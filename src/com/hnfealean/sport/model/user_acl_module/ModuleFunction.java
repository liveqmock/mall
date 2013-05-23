package com.hnfealean.sport.model.user_acl_module;

/**
 * @hibernate.class table="t_acl_modulefuntion"
 * @author Administrator
 *
 */
public class ModuleFunction {

	/**
	 * @hibernate.id generator-class="native"
	 */
	private int id;
	
	/**
	 * @hibernate.property not-null="true" length="254"
	 */
	private String functionName;
	/**
	 * @hibernate.property length="254"
	 */	
	private String functionDescription;
	/**
	 * @hibernate.many-to-one class="com.hnfealean.sport.model.user_acl_module.Module"
	 */
	private Module module;
	public String getFunctionDescription() {
		return functionDescription;
	}
	public void setFunctionDescription(String functionDescription) {
		this.functionDescription = functionDescription;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}

}
