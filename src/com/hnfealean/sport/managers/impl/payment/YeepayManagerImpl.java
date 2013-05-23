package com.hnfealean.sport.managers.impl.payment;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.payment.YeepayManager;
import com.hnfealean.sport.model.payment.yeepay.YeepayRequest;
import com.hnfealean.sport.model.payment.yeepay.YeepayResponse;

public class YeepayManagerImpl extends CommonManager implements YeepayManager {

	public void addYeepayRequest(YeepayRequest request) {
		getHibernateTemplate().save(request);
		
	}

	public void addYeepayResponse(YeepayResponse response) {
		getHibernateTemplate().save(response);
		
	}

}
