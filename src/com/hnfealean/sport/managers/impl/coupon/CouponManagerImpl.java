package com.hnfealean.sport.managers.impl.coupon;

import java.util.List;

import com.hnfealean.sport.managers.coupon.CouponManager;
import com.hnfealean.sport.managers.impl.product.CommonManager;
import com.hnfealean.sport.model.coupon.Coupon;
import com.hnfealean.sport.model.coupon.CouponUsedRecord;
import com.hnfealean.sport.pageset.PageModel;

public class CouponManagerImpl extends CommonManager implements CouponManager {

	public void addCoupon(Coupon coupon) {
		getHibernateTemplate().save(coupon);
	}

	public void addCouponUsedRecord(CouponUsedRecord record) {
		getHibernateTemplate().save(record);
	}

	public boolean deleteCoupon(String id) {
		getSession().createQuery("delete from Coupon where id=?")
		.setParameter(0, id)
		.executeUpdate();
		return true;
	}

	public boolean deleteCoupons(String[] ids) {
		getSession().createQuery("delete from Coupon where id in(?)")
		.setParameter(0, ids)
		.executeUpdate();
		return true;
	}

	public List<Coupon> getAllCoupon(boolean list) {
		return(List<Coupon>)getSession().createQuery("from Coupon").list();
	}

	public PageModel getAllCoupon() {
		PageModel pm = new PageModel();
		pm.setTotal(((Long)getSession().createQuery("select count(id) from Coupon").uniqueResult()).intValue());
		List<Coupon> coupons =(List<Coupon>) getSession().createQuery("from Coupon").list();
		pm.setDatas(coupons);
		return pm;
	}

	public List<CouponUsedRecord> getCouponUsedRecords(boolean list) {
		return(List<CouponUsedRecord>)getSession().createQuery("from CouponUsedRecord").list();
	}

	public PageModel getCouponUsedRecords() {
		PageModel pm = new PageModel();
		pm.setTotal(((Long)getSession().createQuery("select count(id) from CouponUsedRecord").uniqueResult()).intValue());
		List<Coupon> coupons =(List<Coupon>) getSession().createQuery("from CouponUsedRecord").list();
		pm.setDatas(coupons);
		return pm;
	}

	public void updateCoupon(Coupon coupon) {
		getHibernateTemplate().update(coupon);

	}

	public void updateCouponAvaliable(boolean avaliable, String couponId) {
		getSession().createQuery("update Coupon set avaliable =? where id=?")
		.setParameter(0, avaliable)
		.setParameter(1, couponId)
		.executeUpdate();
	
	}

	public Coupon loadCouponById(String id) {
		return (Coupon)getHibernateTemplate().load(Coupon.class, id);
	}

}
