package com.hnfealean.sport.managers.coupon;

import java.util.List;

import com.hnfealean.sport.model.coupon.Coupon;
import com.hnfealean.sport.model.coupon.CouponUsedRecord;
import com.hnfealean.sport.pageset.PageModel;

public interface CouponManager {

	public void addCoupon(Coupon coupon);
	
	public void updateCoupon(Coupon coupon);
	public void updateCouponAvaliable(boolean avaliable,String couponId);
	
	public Coupon loadCouponById(String id);
	
	public List<Coupon> getAllCoupon(boolean list);
	
	public PageModel getAllCoupon();
	
	public boolean deleteCoupon(String id);
	
	public boolean deleteCoupons(String[] ids);
	
	public void addCouponUsedRecord(CouponUsedRecord record);
	public List<CouponUsedRecord> getCouponUsedRecords(boolean list);
	public PageModel getCouponUsedRecords();
}
