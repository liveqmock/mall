package com.hnfealean.sport.managers.impl.coupon;

import com.hnfealean.sport.managers.coupon.FrontCouponManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.coupon.Coupon;

public class FrontCouponManagerImpl extends CommonManager implements
		FrontCouponManager {

	public Coupon checkAvaliable(String couponId) {
		Coupon coupon =(Coupon)getSession().createQuery("from Coupon where id=? and avaliable=?")
		.setParameter(0, couponId)
		.setParameter(1, true)
		.uniqueResult();
		return coupon;
	}

}
