package com.hnfealean.sport.web.actions.shopping;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.shopping.OrderStatusManager;
import com.hnfealean.sport.model.shopping.OrderStatus;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.shopping.OrderStatusForm;
import com.sun.org.apache.commons.beanutils.BeanUtils;

public class OrderStatusAction extends DispatchAction {

	private OrderStatusManager orderStatusManager;

	public void setOrderStatusManager(OrderStatusManager orderStatusManager) {
		this.orderStatusManager = orderStatusManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<OrderStatus> status = orderStatusManager.getAllOrderStatus();
		request.setAttribute("status", status);
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
	
		OrderStatusForm osf = (OrderStatusForm) form;
		if(osf.getName()==null||osf.getName().trim().length()==0)	throw new SystemException("非法操作！");
		OrderStatus status = new OrderStatus();
		BeanUtils.copyProperties(status, osf);
		orderStatusManager.addOrderStatus(status);
		request.setAttribute("message", "添加成功!");
		request.setAttribute("urladdress", "control/shopping/orderstatus/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderStatusForm osf = (OrderStatusForm) form;
		if(osf.getId()==0)	throw new SystemException("非法操作！");
		OrderStatus status = orderStatusManager.getOrderStatus(osf.getId());
		request.setAttribute("status", status);
		return mapping.findForward("edit");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderStatusForm osf = (OrderStatusForm) form;
		if(osf.getId()==0||osf.getName()==null||osf.getName().trim().length()==0)	throw new SystemException("非法操作！");
		OrderStatus status = orderStatusManager.getOrderStatus(osf.getId());
		BeanUtils.copyProperties(status, osf);
		orderStatusManager.updateOrderStatus(status);
		request.setAttribute("message", "更新成功!");
		request.setAttribute("urladdress", "control/shopping/orderstatus/manage.do");
		return mapping.findForward("success");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		OrderStatusForm osf = (OrderStatusForm) form;
		if(osf.getId()==0)throw new SystemException("非法操作！");
		orderStatusManager.deleteOrderStatus(osf.getId());
		request.setAttribute("message", "删除成功!");
		request.setAttribute("urladdress", "control/shopping/orderstatus/manage.do");
		return mapping.findForward("success");
	}
}
