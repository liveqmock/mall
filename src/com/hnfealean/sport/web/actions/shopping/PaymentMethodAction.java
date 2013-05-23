package com.hnfealean.sport.web.actions.shopping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.shopping.PaymentMethodManager;
import com.hnfealean.sport.model.shopping.PaymentMethod;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.shopping.PaymentMethodForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class PaymentMethodAction extends DispatchAction {

	private PaymentMethodManager paymentMethodManager;

	public void setPaymentMethodManager(PaymentMethodManager paymentMethodManager) {
		this.paymentMethodManager = paymentMethodManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<PaymentMethod> paymentMethods = paymentMethodManager.getAllPaymentMethods();
		request.setAttribute("paymentMethods", paymentMethods);
		return mapping.findForward("index");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addInput");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaymentMethodForm pmf =(PaymentMethodForm) form;
		if(pmf.getName()==null||pmf.getName().trim().length()==0)throw new SystemException("非法访问！");
		PaymentMethod paymentMethod = new PaymentMethod();
		BeanUtils.copyProperties( paymentMethod,pmf);
		paymentMethodManager.addPaymentMethod(paymentMethod);
		request.setAttribute("message", "数据添加成功！");
		request.setAttribute("urladdress", "control/center/paymentmethod/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaymentMethodForm pmf =(PaymentMethodForm) form;
		if(pmf.getId()==0)throw new SystemException("非法访问！");
		PaymentMethod paymentMethod = paymentMethodManager.getPaymentMethod(pmf.getId());
		if(paymentMethod==null)throw new SystemException("找不到指定的数据！");
		request.setAttribute("paymentMethod", paymentMethod);
		return mapping.findForward("edit");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaymentMethodForm pmf =(PaymentMethodForm) form;
		if(pmf.getId()==0)throw new SystemException("非法访问！");
		PaymentMethod paymentMethod = paymentMethodManager.getPaymentMethod(pmf.getId());
		BeanUtils.copyProperties(paymentMethod,pmf);
		paymentMethodManager.updatePaymentMethod(paymentMethod);
		request.setAttribute("message", "更新成功！");
		request.setAttribute("urladdress", "control/center/paymentmethod/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward updateEnable(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaymentMethodForm pmf =(PaymentMethodForm) form;
		if(pmf.getId()==0)throw new SystemException("非法访问！");
		paymentMethodManager.updatePaymentMethodEnable(pmf.getId(), pmf.getEnable());
		request.setAttribute("message", "更新成功！");
		request.setAttribute("urladdress", "control/center/paymentmethod/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PaymentMethodForm pmf =(PaymentMethodForm) form;
		if(pmf.getId()==0)throw new SystemException("非法访问！");
		paymentMethodManager.delPaymentMethod(pmf.getId());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/paymentmethod/manage.do");
		return mapping.findForward("success");
	}
}
