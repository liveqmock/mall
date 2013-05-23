package com.hnfealean.sport.web.actions.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.global.CheckUserNameExistsManager;
import com.hnfealean.sport.web.forms.user.UserNameCheckForm;

public class CheckUserNameExistsAction extends DispatchAction {

	private CheckUserNameExistsManager checkUserNameExistsManager;
	
	public void setCheckUserNameExistsManager(
			CheckUserNameExistsManager checkUserNameExistsManager) {
		this.checkUserNameExistsManager = checkUserNameExistsManager;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		return mapping.findForward("wrong");
	}

	public ActionForward checkUserNameExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserNameCheckForm uncf =(UserNameCheckForm) form;
		request.setAttribute("exists", checkUserNameExistsManager.checkUserNameExists(uncf.getUsername()));
		return mapping.findForward("index");
	}
}
