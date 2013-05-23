package com.hnfealean.sport.web.actions.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.hnfealean.sport.managers.user_acl_module.UserManager4Admin;
import com.hnfealean.sport.model.user_acl_module.User;
import com.hnfealean.sport.pageset.PageModel;
import com.hnfealean.sport.web.SystemException;
import com.hnfealean.sport.web.forms.user.UserManagerActionForm;

public class UserManagerAction extends DispatchAction {

	private UserManager4Admin userManager4Admin;
	public void setUserManager4Admin(UserManager4Admin userManager4Admin) {
		this.userManager4Admin = userManager4Admin;
	}
	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PageModel p= userManager4Admin.searchAllUser();
		request.setAttribute("pm", p);
		return mapping.findForward("index");
	}
	public ActionForward getDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserManagerActionForm umaf = (UserManagerActionForm) form;
		if(umaf.getUserId()==0)		throw new SystemException("用户ID不能为空，且需大于0");
		request.setAttribute("user", userManager4Admin.getUserDetail(umaf.getUserId()));
		
		return mapping.findForward("userdetail");
	}
	//editUser
	//deleteUser
	//getOrders
	//findUserPassword
	public ActionForward editUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
			UserManagerActionForm umaf = (UserManagerActionForm) form;
			if(umaf.getUserId()==0)		throw new SystemException("用户ID不能为空，且需大于0");
			request.setAttribute("user", userManager4Admin.getUserDetail(umaf.getUserId()));
			
			return mapping.findForward("useredit");
	}
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {		
			UserManagerActionForm umaf = (UserManagerActionForm) form;
			if(umaf.getUserId()==0)		throw new SystemException("用户ID不能为空，且需大于0");
			//request.setAttribute("user", userManager4Admin.getUserDetail(umaf.getUserId()));
			userManager4Admin.deleteUser(umaf.getUserId());
			request.setAttribute("message", "删除成功!");
			request.setAttribute("urladdress", "control/center/user/manage.do");
			return mapping.findForward("success");
	}
	
	public ActionForward getOrders(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			UserManagerActionForm umaf = (UserManagerActionForm) form;
			if(umaf.getUserId()==0)		throw new SystemException("用户ID不能为空，且需大于0");
			request.setAttribute("orders", userManager4Admin.getUserOrders(umaf.getUserId()));
			return mapping.findForward("userorderlist");
	}
	public ActionForward updateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			UserManagerActionForm umaf = (UserManagerActionForm) form;
			if(umaf.getUserId()==0)		throw new SystemException("用户ID不能为空，且需大于0");
			User user =userManager4Admin.getUserDetail(umaf.getUserId());
			user.setPassword(umaf.getPassword());
			
			return mapping.findForward("success");
	}
	public ActionForward searchUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("index");
	}
	
}
