package com.hnfealean.sport.managers.impl.payment;

import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.managers.payment.AlipayManager;
import com.hnfealean.sport.model.payment.alipay.AlipayConfiguration;
import com.hnfealean.sport.model.payment.alipay.AlipayRequest;
import com.hnfealean.sport.model.payment.alipay.AlipayResponse;

public class AlipayManagerImpl extends CommonManager implements AlipayManager {

	public void addAlipayRequest(AlipayRequest alipayRequest) {
		getHibernateTemplate().save(alipayRequest);
	}

	public void addAlipayResponse(AlipayResponse alipayResponse) {
		getHibernateTemplate().save(alipayResponse);
		
	}

	public AlipayConfiguration getAlipayConfiguration() {
		return	(AlipayConfiguration)getSession().createQuery("from AlipayConfiguration")
		.setMaxResults(1).uniqueResult();
	
	}

	public AlipayConfiguration getAlipayConfigurationById(int id) {
		return (AlipayConfiguration)getHibernateTemplate().load(AlipayConfiguration.class, id);
		
	}

	public void updateAlipayConfiguration(AlipayConfiguration config) {
		getHibernateTemplate().update(config);		
	}

	public void addAlipayConfiguration(AlipayConfiguration config) {
		getHibernateTemplate().save(config);
		
	}
}
