package com.hnfealean.sport.web.forms.shopping;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class FrontPayAndDeliverForm extends ActionForm {

	private int deliverMethod;
	private int timeLimit ;
	private String messageRequire;
	private int paymentMethod ;
	public int getDeliverMethod() {
		return deliverMethod;
	}
	public void setDeliverMethod(int deliverMethod) {
		this.deliverMethod = deliverMethod;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getMessageRequire() {
		return messageRequire;
	}
	public void setMessageRequire(String messageRequire) {
		this.messageRequire = messageRequire;
	}
	public int getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(int paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
