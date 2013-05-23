package com.hnfealean.sport.managers.payment;

import com.hnfealean.sport.model.payment.yeepay.YeepayRequest;
import com.hnfealean.sport.model.payment.yeepay.YeepayResponse;

public interface YeepayManager {

	public void addYeepayRequest(YeepayRequest request);
	public void addYeepayResponse(YeepayResponse response);
}
