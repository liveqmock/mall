package com.hnfealean.sport.web.actions.coupon;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.coupon.CouponManager;
import com.hnfealean.sport.model.coupon.Coupon;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.MD5;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.coupon.CouponActionForm;

public class CouponAction extends DispatchAction {

	private CouponManager couponManager;

	public void setCouponManager(CouponManager couponManager) {
		this.couponManager = couponManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageModel pm = couponManager.getAllCoupon();
		request.setAttribute("pm", pm);
		return mapping.findForward("index");
	}
	public ActionForward addCouponInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			return mapping.findForward("addCouponInput");
	}
	public ActionForward addCoupon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CouponActionForm caf = (CouponActionForm) form;
		char[] codeSequence={'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
				'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 
				'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		StringBuffer sid = new StringBuffer();
		for(int i=0;i<32;i++){
			Random random = new Random();
			sid.append(codeSequence[random.nextInt(codeSequence.length)]);
		}
		String couponId = MD5.MD5Encode((new Date()).toGMTString())+sid.toString();
		System.out.println("couponId:"+couponId);
		
		Coupon coupon = new Coupon();
		coupon.setAvaliable(caf.getAvaliable());
		coupon.setDiscountPrice(caf.getDiscountPrice());
		coupon.setId(couponId);
		coupon.setLimitTimes(caf.getLimitTimes());
		coupon.setMaxTimes(caf.getMaxTimes());
		coupon.setName(caf.getName().trim());
		coupon.setUsedTimes(0);
		couponManager.addCoupon(coupon);
		request.setAttribute("message", "添加成功！");
		request.setAttribute("urladdress", "control/center/coupon/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward deleteCoupon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CouponActionForm caf = (CouponActionForm) form;
		if(caf.getId()==null||caf.getId().trim().length()==0){
			throw new SystemException("非法访问，请指定优惠券的ID");
		}
		couponManager.deleteCoupon(caf.getId().trim());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/coupon/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CouponActionForm caf = (CouponActionForm) form;
		if(caf.getId()==null||caf.getId().trim().length()==0){
			throw new SystemException("非法访问，请指定优惠券的ID");
		}
		Coupon coupon= couponManager.loadCouponById(caf.getId().trim());
		request.setAttribute("coupon", coupon);
		return mapping.findForward("edit");
	}
	public ActionForward updateCoupon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CouponActionForm caf = (CouponActionForm) form;
		if(caf.getId()==null||caf.getId().trim().length()==0){
			throw new SystemException("非法访问，请指定优惠券的ID");
		}
		Coupon coupon= couponManager.loadCouponById(caf.getId().trim());
		if(coupon==null)throw new SystemException("无法找到该优惠券！");
		coupon.setAvaliable(caf.getAvaliable());
		coupon.setDiscountPrice(caf.getDiscountPrice());
		coupon.setLimitTimes(caf.getLimitTimes());
		coupon.setMaxTimes(caf.getMaxTimes());
		coupon.setName(caf.getName());
		couponManager.updateCoupon(coupon);
		request.setAttribute("message", "修改成功！");
		request.setAttribute("urladdress", "control/center/coupon/manage.do");
		return mapping.findForward("success");
	}
/*	public ActionForward addCouponUsedRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CouponActionForm caf = (CouponActionForm) form;
		if(caf.getId()==null||caf.getId().trim().length()==0){
			throw new SystemException("非法访问，请指定优惠券的ID");
		}
		WebUtil.getUserEmail(request);
		return mapping.findForward("success");
	}*/
	public ActionForward deleteCouponUsedRecord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		CouponActionForm caf = (CouponActionForm) form;
		if(caf.getId()==null||caf.getId().trim().length()==0){
			throw new SystemException("非法访问，请指定优惠券的ID");
		}
		couponManager.deleteCoupon(caf.getId().trim());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/coupon/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward listCouponUsedRecords(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("pm",couponManager.getCouponUsedRecords());
		return mapping.findForward("listCouponUsedRecords");
	}
}
