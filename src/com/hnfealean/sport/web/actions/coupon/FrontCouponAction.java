package com.hnfealean.sport.web.actions.coupon;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.coupon.FrontCouponManager;
import com.hnfealean.sport.model.coupon.Coupon;
import com.hnfealean.sport.web.WebUtil;
import com.hnfealean.sport.web.forms.coupon.FrontCouponActionForm;

public class FrontCouponAction extends DispatchAction {

	private FrontCouponManager frontCouponManager;

	public void setFrontCouponManager(FrontCouponManager frontCoiuponManager) {
		this.frontCouponManager = frontCoiuponManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if(WebUtil.getUserId(request)==0){
			response.reset();
			response.getWriter().write("hnfealean.coupon([false,0])");
			response.flushBuffer();
			return null;
		}
		FrontCouponActionForm fcaf =(FrontCouponActionForm) form;
		if(fcaf.getId()==null||fcaf.getId().trim().length()==0){
			response.reset();
			response.getWriter().write("hnfealean.coupon([false,0])");
			response.flushBuffer();
			return null;
		}
		Coupon coupon = frontCouponManager.checkAvaliable(fcaf.getId().trim()) ;
		if(coupon==null){
			response.reset();
			response.getWriter().write("hnfealean.coupon([false,0])");
			response.flushBuffer();
			return null;
		}
		if(coupon.getLimitTimes()==true){//该券限制使用次数
			if(coupon.getUsedTimes()<coupon.getMaxTimes()){//表示该券可用
				response.reset();
				response.getWriter().write("hnfealean.coupon([true,"+coupon.getDiscountPrice()+"])");
				response.flushBuffer();
				return null;
			}
		}
		//request.getSession().setAttribute("coupon", coupon);
		//该券不限次数
		response.reset();
		response.getWriter().write("hnfealean.coupon([true,"+coupon.getDiscountPrice()+"])");
		response.flushBuffer();
		return null;
	}

}
