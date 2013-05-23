package com.hnfealean.sport.managers.payment;

import java.util.List;

import com.hnfealean.sport.model.payment.paypal.Paypal;
import com.hnfealean.sport.pageset.PageModel;

public interface PaypalManager {

	public boolean addPaypal(Paypal paypal);
	public boolean deletePaypal(int id);
	public boolean updatePaypal(Paypal paypal,int id);
	
	public List<Paypal> searchAllPaypal();
	public PageModel searchPaypal();
	public List<Paypal> getPaypalByIds(int[] ids);
	
}
