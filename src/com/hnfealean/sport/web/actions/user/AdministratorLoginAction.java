package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.AdminLoginManager;
import com.hnfealean.sport.model.user_acl_module.Administrator;
import com.hnfealean.sport.web.forms.user.AdministratorForm;

public class AdministratorLoginAction extends DispatchAction {

	private AdminLoginManager adminLoginManager ;

	public void setAdminLoginManager(AdminLoginManager adminLoginManager) {
		this.adminLoginManager = adminLoginManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		AdministratorForm af = (AdministratorForm) form;
		if(af.getName()== null || af.getName().trim().length()==0){
			return mapping.findForward("index");
			//throw new SystemException("请勿提交非法数据！");
		}
		Administrator admin = adminLoginManager.login(af.getName().trim(),af.getPassword().trim());
		if(admin!=null){
			request.getSession().setAttribute("administrator", admin);
		}
		request.setAttribute("directUrl", "control/center/main.do");
		return mapping.findForward("redirectTo");
	}
	public ActionForward addInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("input");
	}
	
}
