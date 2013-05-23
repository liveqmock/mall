package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.global.CheckUserLoginManager;
import com.hnfealean.sport.web.forms.user.UserLoginCheckForm;

public class CheckUserLoginAction extends DispatchAction {

	private CheckUserLoginManager checkUserLoginManager;
	

	public void setCheckUserLoginManager(CheckUserLoginManager checkUserLoginManager) {
		this.checkUserLoginManager = checkUserLoginManager;
	}
	
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserLoginCheckForm ulcf = (UserLoginCheckForm) form;
		if(ulcf.getUsername().trim().length() == 0 || ulcf.getPassword().trim().length() == 0){
			return mapping.findForward("wrong");
		}
		boolean login = checkUserLoginManager.checkLogin(ulcf.getUsername().trim(), ulcf.getPassword().trim());
		if(login){
			request.setAttribute("comeUrl", "");
			return mapping.findForward("index");
		}
		return mapping.findForward("wrong");
	}

}
