package com.hnfealean.sport.managers.shopping;

import java.util.List;

import com.hnfealean.sport.model.shopping.PaymentMethod;

public interface PaymentMethodManager {

	public void addPaymentMethod(PaymentMethod paymentMethod);
	public PaymentMethod getPaymentMethod(int id);
	public void updatePaymentMethod(PaymentMethod paymentMethod);
	public void updatePaymentMethodEnable(int id,boolean enable);
	public void delPaymentMethod(int id);
	public List<PaymentMethod> getAllPaymentMethods();

}
