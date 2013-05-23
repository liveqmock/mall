package com.hnfealean.sport.managers.payment;

import com.hnfealean.sport.model.payment.alipay.AlipayConfiguration;
import com.hnfealean.sport.model.payment.alipay.AlipayRequest;
import com.hnfealean.sport.model.payment.alipay.AlipayResponse;

public interface AlipayManager {
	//public String generateSign(AlipayRequest alipayRequest);
	public void addAlipayRequest(AlipayRequest alipayRequest);
	public void addAlipayResponse(AlipayResponse alipayResponse);
	public AlipayConfiguration getAlipayConfiguration();
	public AlipayConfiguration getAlipayConfigurationById(int id);
	public void updateAlipayConfiguration(AlipayConfiguration config);
	public void addAlipayConfiguration(AlipayConfiguration config);
}
