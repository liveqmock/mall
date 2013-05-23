package com.hnfealean.sport.web.actions.deliver;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.deliver.DeliverTypeManager;
import com.hnfealean.sport.model.deliver.DeliverType;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.deliver.DeliverTypeActionForm;

public class DeliverTypeAction extends DispatchAction {

	private DeliverTypeManager deliverTypeManager;

	public void setDeliverTypeManager(DeliverTypeManager deliverTypeManager) {
		this.deliverTypeManager = deliverTypeManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<DeliverType> deliverTypes = deliverTypeManager.getAllDeliverTypes();
		request.setAttribute("deliverTypes", deliverTypes);
		return mapping.findForward("index");
	}
	public ActionForward editinput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverTypeActionForm dtaf = (DeliverTypeActionForm)form;
		if(dtaf.getId()==0)	throw new SystemException("非法访问!");
		request.setAttribute("delivertype", deliverTypeManager.getDeliverTypeById(dtaf.getId()));
		return mapping.findForward("editinput");
	}	
	public ActionForward addinput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		return mapping.findForward("addinput");
	}	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverTypeActionForm dtaf = (DeliverTypeActionForm)form;
		if(dtaf.getName()==null||dtaf.getName().trim().length()==0)	throw new SystemException("配送方式的名称不能为空!");
		DeliverType type = new DeliverType();
		type.setDescription(dtaf.getDescription());
		type.setName(dtaf.getName());
		deliverTypeManager.addDeliverType(type);
		request.setAttribute("message", "添加成功！");
		request.setAttribute("urladdress", "control/center/delivertype/manage.do");
		return mapping.findForward("success");

	}	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverTypeActionForm dtaf = (DeliverTypeActionForm)form;
		if(dtaf.getId()==0)	throw new SystemException("非法访问!");
		DeliverType type= deliverTypeManager.getDeliverTypeById(dtaf.getId());
		if(type==null)throw new SystemException("不存在该配送方式");
		type.setDescription(dtaf.getDescription());
		type.setName(dtaf.getName());
		deliverTypeManager.updateDeliverType(type);
		request.setAttribute("message", "更新成功！");
		request.setAttribute("urladdress", "control/center/delivertype/manage.do");
		return mapping.findForward("success");

	}	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DeliverTypeActionForm dtaf = (DeliverTypeActionForm)form;
		if(dtaf.getId()==0)	throw new SystemException("非法访问!");
		deliverTypeManager.deleteDeliverType(dtaf.getId());
		request.setAttribute("message", "删除成功！");
		request.setAttribute("urladdress", "control/center/delivertype/manage.do");
		return mapping.findForward("success");

	}	
}
